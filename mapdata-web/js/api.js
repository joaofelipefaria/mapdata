const BASE_URL = 'http://localhost:8080/api/mapdata';

// Function to query data
function queryData() {
    clearMessage('message'); // Clear messages before new operation

    fetch(`${BASE_URL}/all`) 
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#dataTable tbody');
            tableBody.innerHTML = '';
            data.forEach(item => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${item.id}</td>
                    <td>${item.value}</td>
                    <td>
                        <button onclick="showDetails(${item.id})">Details</button>
                        <button onclick="editData(${item.id})">Edit</button>
                        <button onclick="deleteData(${item.id})">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
            showElement('dataTableContainer');
            showElement('createDataButton');
            showMessage('message', 'Data queried successfully!');
            hideElement('createForm');
            hideElement('detailsSection');
            hideElement('metadataFormContainer');
        })
        .catch(error => {
            console.error('Error querying data:', error);
            showMessage('message', 'Error querying data.', true);
        });
}

// Function to handle form submission for Mapdata
function handleSubmit(event) {
    event.preventDefault();
    clearMessage('message'); // Clear messages before new operation

    const id = document.getElementById('mapdataId').value;
    const value = document.getElementById('value').value;
    const method = id ? 'PUT' : 'POST';
    const url = id ? `${BASE_URL}/${id}` : BASE_URL;
    const requestBody = id ? { id, value } : { value };

    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(requestBody)
    })
    .then(response => {
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
        return response.json();
    })
    .then(() => {
        queryData();
        hideElement('createForm');
        showElement('createDataButton');
        document.getElementById('dataForm').reset();
        showMessage('message', 'Data saved successfully!');
    })
    .catch(error => {
        console.error('Error saving data:', error);
        showMessage('message', 'Error saving data.', true);
    });
}

// Function to delete Mapdata
function deleteData(id) {
    fetch(`${BASE_URL}/${id}`, { method: 'DELETE' })
        .then(response => {
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
            queryData();
            showMessage('message', 'Mapdata deleted successfully!');
        })
        .catch(error => {
            console.error('Error deleting data:', error);
            showMessage('message', 'Error deleting data.', true);
        });
}

// Function to edit Mapdata
function editData(id) {
    fetch(`${BASE_URL}/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('mapdataId').value = id;
            document.getElementById('value').value = data.value;
            showElement('createForm');
            hideElement('createDataButton');
            hideElement('detailsSection');
        })
        .catch(error => {
            console.error('Error fetching data for edit:', error);
            showMessage('message', 'Error fetching data for edit.', true);
        });
}
