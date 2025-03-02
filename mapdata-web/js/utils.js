// Function to display messages
function showMessage(messageElementId, message, isError = false) {
    const messageContainer = document.getElementById(messageElementId);
    messageContainer.innerHTML = message;
    messageContainer.style.color = isError ? 'red' : 'green';
}

function appendMessage(messageElementId, message, isError = false) {
    const messageContainer = document.getElementById(messageElementId);
    messageContainer.innerHTML += message;
    messageContainer.style.color = isError ? 'red' : 'green';
}

// Function to clear messages
function clearMessage(messageElementId) {
    const messageContainer = document.getElementById(messageElementId);
    messageContainer.innerHTML = '';
}

// Helper function to show elements
function showElement(elementId) {
    document.getElementById(elementId).style.display = 'block';
}

// Helper function to hide elements
function hideElement(elementId) {
    document.getElementById(elementId).style.display = 'none';
}
