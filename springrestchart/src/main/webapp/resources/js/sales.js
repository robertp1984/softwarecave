
function renderPlot(data) {
    $("#chart").empty();
    $("#chartError").hide();

    var countryName = data.country.name;
    var values = data.values;
    $.jqplot("chart", [values], {
        title: "<h2> Sales in " + countryName + " in current month</h2>",
        axes: {
            xaxis: {
                label: 'Day of month',
                pad: 0
            },
            yaxis: {
                label: 'USD'
            }
        }});
}

function renderError() {
    $("#chart").empty();
    $("#chartError").show();
}

function reloadPlot(countryCode) {
    $.ajax({
        url: encodeURI("services/sales/" + countryCode),
        type: "GET",
        dataType: "json",
        success: renderPlot,
        error: renderError
    });
}

function initializeCountries() {
    $("#countrySelect").change(function() {
        var countryCode = $(this).val();
        reloadPlot(countryCode);
    });
    reloadPlot($("#countrySelect").val());
}

$(document).ready(initializeCountries);
