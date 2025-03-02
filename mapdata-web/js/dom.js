// Function to show the create form
function showCreateForm() {
    showElement('createForm');
    hideElement('createDataButton');
    document.getElementById('mapdataId').value = '';
    document.getElementById('value').value = '';
    clearMessage('message'); // Clear messages before new operation
}

// Function to show the details of a Mapdata
function showDetails(id) {
    currentMapdataId = id;
    clearMessage('message'); // Clear messages before new operation

    fetch(`${BASE_URL}/${id}/metadata`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(metadata => {
            console.log('Metadata fetched:', metadata); // Debugging: log the metadata response
            if (metadata && Array.isArray(metadata) && metadata.length > 0) {
                const metadataTableBody = document.querySelector('#metadataTable tbody');
                metadataTableBody.innerHTML = '';
                metadata.forEach(item => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${item.id}</td>
                        <td>${item.value1}</td>
                        <td>${item.value2}</td>
                        <td>
                            <button onclick="editMetadata(${item.id})">Edit</button>
                            <button onclick="deleteMetadata(${item.id})">Delete</button>
                        </td>
                    `;
                    metadataTableBody.appendChild(row);
                });
                showMessage('message', 'Metadata loaded successfully!');
            } else {
                console.log('No metadata found for this Mapdata.'); // Debugging: log that no metadata was found
                showMessage('message', 'No metadata found for this mapdata.', true);
            }
        })
        .catch(error => {
            console.error('Error fetching metadata:', error);
            showMessage('message', 'Error fetching metadata.', true);
            console.log('No metadata found for this Mapdata.'); // Debugging: log that no metadata was found
            appendMessage('message', 'No metadata found for this mapdata.', true);
            hideElement('createForm');
            hideElement('metadataTable');
        });

    showElement('detailsSection');
    showElement('metadataTable');
    showElement('createMetadataButton');
    hideElement('createForm');
}

// Function to delete metadata
function deleteMetadata(metadataId) {
    fetch(`${BASE_URL}/${currentMapdataId}/metadata/${metadataId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        showDetails(currentMapdataId);
        showMessage('message', 'Metadata deleted successfully!');
    })
    .catch(error => {
        console.error('Error deleting metadata:', error);
        showMessage('message', 'Error deleting metadata.', true);
    });
}

// Function to show the create metadata form
function showCreateMetadataForm() {
    showElement('metadataFormContainer');
    hideElement('createMetadataButton');
    clearMessage('message'); // Clear messages before new operation
}

// Function to handle metadata form submission
function handleMetadataSubmit(event) {
    event.preventDefault();
    clearMessage('message'); // Clear messages before new operation

    const id = document.getElementById('metadataId').value;
    const value1 = document.getElementById('value1').value;
    const value2 = document.getElementById('value2').value;
    const method = id ? 'PUT' : 'POST';
    const url = id ? `${BASE_URL}/${currentMapdataId}/metadata/${id}` : `${BASE_URL}/${currentMapdataId}/metadata`;

    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ value1, value2 })
    })
    .then(response => response.json())
    .then(() => {
        showDetails(currentMapdataId);
        hideElement('metadataFormContainer');
        showElement('createMetadataButton');
        document.getElementById('metadataForm').reset();
        showMessage('message', 'Metadata saved successfully!');
    })
    .catch(error => {
        console.error('Error saving metadata:', error);
        showMessage('message', 'Error saving metadata.', true);
    });
    document.getElementById('metadataId').value = '';
    document.getElementById('value1').value = '';
    document.getElementById('value2').value = '';
}
