const count = 20;
let itemIdx = 0;
let isLoading = false;

function showLoading() {
    document.querySelector('.spinner').style.display = 'block';
}

function hideLoading() {
    document.querySelector('.spinner').style.display = 'none';
}

const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        if (entry.isIntersecting && !isLoading) {
            loadMoreItems();
        }
    })
}, {root: null, threshold: 0.1});

function loadMoreItems() {
    isLoading = true;
    showLoading();

    setTimeout(() => {
        const list = document.querySelector('.list');
        for (let i = itemIdx; i < itemIdx + count; i++) {
            let item = document.createElement('p');
            item.textContent = i;
            item.className += 'item';
            list.appendChild(item);
        }
        itemIdx += count;

        hideLoading();
        isLoading = false;
    }, 1000);
}

observer.observe(document.querySelector('.end'))