document.addEventListener('DOMContentLoaded', function() {
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const services = document.querySelectorAll('.service');
    const dots = document.querySelectorAll('.dot');
    let currentService = 0;

    function showService(index) {
        services.forEach((service, i) => {
            service.classList.toggle('active', i === index);
            dots[i].classList.toggle('active', i === index);
        });
    }

    prevBtn.addEventListener('click', function() {
        if (currentService > 0) {
            currentService--;
        } else {
            currentService = services.length - 1;
        }
        showService(currentService);
    });

    nextBtn.addEventListener('click', function() {
        if (currentService < services.length - 1) {
            currentService++;
        } else {
            currentService = 0;
        }
        showService(currentService);
    });

    dots.forEach((dot, index) => {
        dot.addEventListener('click', () => {
            currentService = index;
            showService(currentService);
        });
    });

    showService(currentService);
});
