$(document).ready(function () {
    const message = localStorage.getItem("successMessage");
    if (message) {
        $("#toastMessage").text(message); 
        const toastElement = $("#toastSuccess")[0]; 
        const toast = new bootstrap.Toast(toastElement); 
        toast.show(); 

        localStorage.removeItem("successMessage"); 
    }
});