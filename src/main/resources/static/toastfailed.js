$(document).ready(function () {
    const message = localStorage.getItem("failedMessage");
    if (message) {
        $("#toastMessage").text(message); 
        const toastElement = $("#toastFailed")[0]; 
        const toast = new bootstrap.Toast(toastElement); 
        toast.show(); 

        localStorage.removeItem("failedMessage"); 
    }
});
