// main.js — small helper to show logged in user, add logout, and set active nav link
(function() {
    function getLoggedInUser() {
        try { return localStorage.getItem('loggedInUser'); } catch (e) { return window.loggedInUser || null; }
    }
    function setNavUser() {
        const nav = document.querySelector('.nav-links');
        if (!nav) return;
        const right = nav.querySelector('.nav-right');
        if (!right) return;
        // clear existing contents so we can re-render without shifting layout
        right.innerHTML = '';
        const user = getLoggedInUser();
        if (user) {
            // remove any existing login button/link in nav-left to avoid duplicate controls
            const left = nav.querySelector('.nav-left');
            if (left) {
                const existingLoginBtn = left.querySelector('.header-btn, a[href="login.html"]');
                if (existingLoginBtn) existingLoginBtn.remove();
            }
            const span = document.createElement('span');
            span.className = 'nav-user';
            span.textContent = user;
            const btn = document.createElement('button');
            btn.className = 'logout-btn';
            btn.textContent = 'Logout';
            btn.addEventListener('click', function() {
                try { localStorage.removeItem('loggedInUser'); } catch (e) {}
                // reload to update UI
                window.location.href = 'index.html';
            });
            right.appendChild(span);
            right.appendChild(btn);
        } else {
            // if user not logged in, only add a Login link in the right area
            // if the left side already contains a login control (button or link), don't duplicate it
            const left = nav.querySelector('.nav-left');
            const leftHasLogin = left && (left.querySelector('.header-btn') || left.querySelector('a[href="login.html"]'));
            if (!leftHasLogin) {
                const a = document.createElement('a');
                a.href = 'login.html';
                a.className = 'nav-link';
                a.textContent = 'Login';
                right.appendChild(a);
            }
        }
    }
    function markActiveLink() {
        const links = document.querySelectorAll('.nav-link');
        const current = window.location.pathname.split('/').pop();
        links.forEach(l => {
            const href = l.getAttribute('href');
            if (!href) return;
            if (href.endsWith(current) || (current === '' && href.includes('index.html'))) {
                l.classList.add('active');
            }
        });
    }
    // run on DOMContentLoaded
    document.addEventListener('DOMContentLoaded', function() {
        setNavUser();
        markActiveLink();
        // when user is logged out, clicking Profile should open the login UI (modal if present) or go to login page
        const profileLinks = document.querySelectorAll('a[href="profile.html"]');
        profileLinks.forEach(a => {
            a.addEventListener('click', function(ev){
                const user = getLoggedInUser();
                if (!user){
                    ev.preventDefault();
                    // try to open an on-page login modal if present
                    const modal = document.getElementById('loginModal') || document.querySelector('.modal.login') || document.querySelector('.modal');
                    if (modal){
                        modal.classList.add('active');
                        // focus first input if present
                        const firstInput = modal.querySelector('input, button, textarea, [tabindex]');
                        if (firstInput) firstInput.focus();
                        return;
                    }
                    // fallback: go to login page
                    window.location.href = 'login.html';
                }
            });
        });
    });
})();
