function calculatePiListener(data) {
    var statusElement = document.getElementById("status");
    if (data.status === "begin")
        statusElement.innerHTML = "Sent request. Waiting for response...";
    else if (data.status === "complete")
        statusElement.innerHTML = "Response received";
    else if (data.status === "success")
        statusElement.innerHTML = "Rendered successfully";
}

function calculatePiError(data) {
    var statusElement = document.getElementById("status");
    statusElement.innerHTML = "<b>Error</b> <i>" + data.description + "</i>";
}
