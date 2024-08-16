const count = 20;
let itemIdx = 0;

const observer = new IntersectionObserver((entries, observer) => {
        entries.forEach(entry => {
            const list = document.querySelector('.list');
            if (entry.isIntersecting) {
                entry.target.src = entry.target.dataset.src;
                observer.unobserve(entry.target);
            }
        })
    }, {threshold: 1}
);

const images = document.querySelectorAll('img');
images.forEach(image => {
    observer.observe(image);
})