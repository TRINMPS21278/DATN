let success = document.getElementById('success');
let error = document.getElementById('error');
let warning = document.getElementById('warning');
let info = document.getElementById('info');
let notifications = document.querySelector('.notifications');

function createToast(type, icon, title, text) {
	let newToast = document.createElement('div');
	newToast.innerHTML = `
        <div class="thongbao ${type}">
                <i class="${icon}"></i>
                <div class="content">
                    <div class="title">${title}</div>
                    <span>${text}</span>
                </div>
                <i class="close fa-solid fa-xmark"
                onclick="(this.parentElement).remove()"
                ></i>
            </div>`;

	notifications.appendChild(newToast);
	newToast.timeOut = setTimeout(() => newToast.remove(), 2000)
}

success.onclick = function() {
	let type = 'success';
	let icon = 'fa-solid fa-circle-check';
	let title = 'Thông báo';
	let text = 'Đã thêm vào giỏ hàng.';
	createToast(type, icon, title, text);
}
error.onclick = function() {
	let type = 'error';
	let icon = 'fa-solid fa-circle-exclamation';
	let title = 'Error';
	let text = 'This is a error toast.';
	createToast(type, icon, title, text);
}
warning.onclick = function() {
	let type = 'warning';
	let icon = 'fa-solid fa-triangle-exclamation';
	let title = 'Warning';
	let text = 'This is a warning toast.';
	createToast(type, icon, title, text);
}
info.onclick = function() {
	let type = 'info';
	let icon = 'fa-solid fa-circle-info';
	let title = 'Thông báo';
	let text = 'Đã thêm vào giỏ hàng.';
	createToast(type, icon, title, text);
}


