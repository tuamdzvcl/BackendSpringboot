// auth.js

function initAuth() {
    const role = localStorage.getItem("username");
    const roleElement = document.getElementById("role");
    const logoutBtn = document.getElementById("logoutBtn");
  
    // Render role và đổi text nút login/logout
    if (role) {
      if (roleElement) roleElement.textContent = role;
      if (logoutBtn) logoutBtn.textContent = "Đăng xuất";
    } else {
      if (roleElement) roleElement.textContent = "";
      if (logoutBtn) logoutBtn.textContent = "Đăng nhập";
    }
  
    // Hàm logout chung
    function logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      localStorage.removeItem("loginTime"); // xoá cả loginTime nếu có
      alert("Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại!");
      window.location.href = "/admin-signin.html";
    }
  
    // Tính thời gian login (để cứng 15p từ lúc login)
    const loginTime = localStorage.getItem("loginTime");
    if (role && loginTime) {
      const now = Date.now();
      const diff = now - parseInt(loginTime);
  
      if (diff >= 15 * 60 * 1000) {
        logout();
      } else {
        setTimeout(logout, 15 * 60 * 1000 - diff);
      }
    }
  
    // Gắn sự kiện click nút logout
    if (logoutBtn) {
      logoutBtn.addEventListener("click", function (e) {
        e.preventDefault();
        if (localStorage.getItem("role")) {
          logout();
        } else {
          window.location.href = "./admin-signin.html";
        }
      });
    }
  }
  