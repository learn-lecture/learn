const count = 20;
let itemIdx = 0;

const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        const list = document.querySelector('.list');
        if (entry.isIntersecting) {
            for (let i = itemIdx; i < itemIdx + count; i++) {
                let item = document.createElement('p');
                item.textContent = i;
                item.className += 'item';
                list.appendChild(item);
            }
            itemIdx += count;
        }
    })
}, {root: null, threshold: 0.1});

observer.observe(document.querySelector('.end'))