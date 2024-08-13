const toggleThemeBtn = document.querySelector('.header__theme_button');

document.onload = setInitialTheme(localStorage.getItem('theme'));

function setInitialTheme(theme) {
    if (theme === 'dark') {
        document.documentElement.classList.add('darkTheme');
    } else {
        document.documentElement.classList.remove('darkTheme');
    }
}

toggleThemeBtn.addEventListener('click', () => {
    document.documentElement.classList.toggle('darkTheme');

    if (document.documentElement.classList.contains('darkTheme')) {
        localStorage.setItem('theme', 'dark');
    } else {
        localStorage.setItem('theme', 'light');
    }
})