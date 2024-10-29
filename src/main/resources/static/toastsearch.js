$(document).ready(function () {
    const message = localStorage.getItem("FailedSearch");
    if (message) {
        $("#toastMessageSearched").text(message); 
        const toastElement = $("#toastSearchFailed")[0]; 
        const toast = new bootstrap.Toast(toastElement); 
        toast.show(); 

        localStorage.removeItem("FailedSearch"); 
    }
});
