$(document).ready(function () {
    const message = localStorage.getItem("failedSearchMessage");
    if (message) {
        $("#toastMessageSearch").text(message); 
        const toastElement = $("#toastFailedSearch")[0]; 
        const toast = new bootstrap.Toast(toastElement); 
        toast.show(); 

        localStorage.removeItem("failedSearchMessage"); 
    }
});