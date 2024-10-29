$(document).ready(function () {
    const message = localStorage.getItem("editSuccessMessage");
    if (message) {
        $("#toastMessageEdited").text(message);
        const toastElement = $("#toastEditedSuccess")[0]; 
        const toast = new bootstrap.Toast(toastElement); 
        toast.show();

        localStorage.removeItem("editSuccessMessage"); 
    }
});
