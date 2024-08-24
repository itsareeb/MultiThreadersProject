function loadScheduleAndAppointments() {
    const today = new Date();
    const scheduleTable = document.getElementById('scheduleTable');
    const appointmentTable = document.getElementById('appointmentTable');

    // Clear existing rows
    scheduleTable.innerHTML = '';
    appointmentTable.innerHTML = '';

    for (let i = -3; i <= 3; i++) {
        const date = new Date();
        date.setDate(today.getDate() + i);
        const dateString = date.toLocaleDateString('en-GB');

        if (i >= 0) {
            // Add rows for schedule (future dates)
            const row = `
                <tr>
                    <td><input type="text" class="doctor-id" value="DOC00${i + 1} "></td>
                    <td><input type="date" class="form-control" value="${date.toISOString().split('T')[0]}"></td>
                    <td><input type="checkbox" checked data-toggle="toggle" data-on="Available" data-off="Not Available"></td>
                    <td><input type="checkbox" checked data-toggle="toggle" data-on="Available" data-off="Not Available"></td>
                    <td><input type="checkbox" checked data-toggle="toggle" data-on="Available" data-off="Not Available"></td>
                    <td><input type="checkbox" checked data-toggle="toggle" data-on="Available" data-off="Not Available"></td>
                    <td><button class="btn btn-primary btn-sm edit-btn">Edit</button></td>
                </tr>`;
            scheduleTable.innerHTML += row;
        }

        if (i <= 0) {
            // Add rows for appointments (past and present dates)
            const row = `
                <tr>
                    <td>Appointment details for ${dateString}</td>
                    <td >
                        <button style="background-color:  rgb(145, 255, 172);" class="btn btn-sm">started</button>
                        <button style="background-color:  rgb(145, 255, 172);" class="btn btn-sm">ongoing</button>
                        <button style="background-color:  rgb(145, 255, 172);" class="btn  btn-sm">Completed</button>
                    </td>
                    <td>
                        <button class="btn btn-success btn-sm">Suggest Medical Tests</button>
                        <button class="btn btn-warning btn-sm">Suggest Medicines</button>
                        <button class="btn btn-danger btn-sm">Cancel Appointment</button>
                    </td>
                </tr>`;
            appointmentTable.innerHTML += row;
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    loadScheduleAndAppointments();

    document.querySelectorAll('.edit-btn').forEach(button => {
        button.addEventListener('click', function () {
            const doctorIds = Array.from(document.querySelectorAll('.doctor-id')).map(input => input.value.trim());
            const uniqueIds = new Set(doctorIds);

            if (doctorIds.includes('') || uniqueIds.size !== doctorIds.length) {
                document.getElementById('error-message').style.display = 'block';
            } else {
                document.getElementById('error-message').style.display = 'none';
                // Add your update logic here
                alert('Schedule updated successfully!');
            }
        });
    });
});