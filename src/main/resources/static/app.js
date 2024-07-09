layui.use(['laypage', 'layer'], function() {
    const laypage = layui.laypage;
    const layer = layui.layer;

    function fetchDataCenters(page, size) {
        fetch(`/datacenters?page=${page - 1}&size=${size}`)
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('dataCenterTableBody');
                tableBody.innerHTML = ''; // Clear existing table rows
                data.content.forEach(dataCenter => {
                    const row = document.createElement('tr');
                    const idCell = document.createElement('td');
                    idCell.textContent = dataCenter.id;
                    const nameCell = document.createElement('td');
                    nameCell.textContent = dataCenter.name;
                    row.appendChild(idCell);
                    row.appendChild(nameCell);
                    tableBody.appendChild(row);
                });

                // Initialize pagination
                laypage.render({
                    elem: 'pagination',
                    count: data.totalElements,
                    limit: size,
                    curr: page,
                    jump: function(obj, first) {
                        if (!first) {
                            fetchDataCenters(obj.curr, obj.limit);
                        }
                    }
                });
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                layer.msg('Error fetching data');
            });
    }

    // Initialize with the first page
    fetchDataCenters(1, 10);
});
