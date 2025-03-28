// 함수 정의: 현재 보이는 collapse 요소에서 중복 제목 확인
function highlightDuplicatesInVisibleCollapses() {
    const seenTitles = {};
    const visibleCollapses = document.querySelectorAll('.collapse.show');

    visibleCollapses.forEach((collapse) => {
        const lectureTitles = collapse.querySelectorAll('span._29VsDL');
        lectureTitles.forEach((lecture) => {
            const title = lecture.innerText.trim();
            if (seenTitles[title]) {
                if (!lecture.querySelector('.duplicate-tag')) {
                    lecture.style.backgroundColor = 'yellow';
                    const duplicateTag = document.createElement('span');
                    duplicateTag.style.color = 'red';
                    duplicateTag.innerText = ' (중복)';
                    duplicateTag.classList.add('duplicate-tag');
                    lecture.appendChild(duplicateTag);
                }
            } else {
                seenTitles[title] = true;
            }
        });
    });
}

// 함수 정의: collapse 요소의 show 상태 토글
function handleCollapse(event) {
    const collapseElement = event.currentTarget.querySelector('.collapse');
    if (collapseElement) {
        collapseElement.classList.toggle('show'); // collapse 요소 보이기/숨기기 토글
        highlightDuplicatesInVisibleCollapses(); // 중복 확인
    }
}

// 함수 정의: 새로운 항목에 이벤트 리스너 추가
function addEventListenersToNewItems(items) {
    items.forEach((item) => {
        item.addEventListener('click', handleCollapse);
    });
}

// 함수 정의: DOM 변화를 감지하고 처리
function observeMutations() {
    const observer = new MutationObserver((mutations) => {
        mutations.forEach((mutation) => {
            mutation.addedNodes.forEach((node) => {
                if (node.nodeType === Node.ELEMENT_NODE) {
                    if (node.matches('li._2dEI31')) {
                        addEventListenersToNewItems([node]);
                    } else {
                        const newItems = node.querySelectorAll('li._2dEI31');
                        if (newItems.length > 0) {
                            addEventListenersToNewItems(newItems);
                        }
                    }
                }
            });

            if (mutation.type === 'attributes' && mutation.attributeName === 'class') {
                if (mutation.target.classList.contains('collapse') && mutation.target.classList.contains('show')) {
                    highlightDuplicatesInVisibleCollapses();
                }
            }
        });
    });

    const config = { childList: true, subtree: true, attributes: true, attributeFilter: ['class'] };
    observer.observe(document.body, config);
}

// DOMContentLoaded 이벤트 처리
document.addEventListener('DOMContentLoaded', () => {
    observeMutations();
});