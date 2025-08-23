# Hệ thống đăng nhập với Cookie và Session

Đây là một ứng dụng Java Web đơn giản minh họa cách sử dụng cookie và session để quản lý phiên đăng nhập.

## Tính năng

- ✅ Đăng nhập với Cookie
- ✅ Đăng nhập với Session
- ✅ Bảo vệ trang với Filter
- ✅ Đăng xuất và xóa cookie/session
- ✅ Giao diện đẹp và thân thiện
- ✅ Hỗ trợ tiếng Việt
- ✅ So sánh ưu nhược điểm của Cookie vs Session

## Cấu trúc dự án

```
src/main/java/com/ltw/bt2308/
├── LoginServlet.java          # Xử lý đăng nhập với Cookie
├── LogoutServlet.java         # Xử lý đăng xuất Cookie
├── LoginFilter.java           # Filter bảo vệ trang Cookie
├── DashboardServlet.java      # Trang dashboard Cookie
├── ProfileServlet.java        # Trang hồ sơ cá nhân Cookie
├── SessionLoginServlet.java   # Xử lý đăng nhập với Session
├── SessionLogoutServlet.java  # Xử lý đăng xuất Session
├── SessionFilter.java         # Filter bảo vệ trang Session
├── SessionDashboardServlet.java # Trang dashboard Session
├── SessionProfileServlet.java # Trang hồ sơ cá nhân Session
└── HelloServlet.java          # Servlet mẫu

src/main/webapp/
├── index.jsp             # Trang chủ
└── WEB-INF/
    └── web.xml           # Cấu hình web
```

## Cách sử dụng

### 1. Khởi chạy ứng dụng

```bash
# Biên dịch và chạy với Maven
mvn clean package
mvn tomcat7:run
```

Hoặc sử dụng Maven wrapper:
```bash
./mvnw clean package
./mvnw tomcat7:run
```

### 2. Truy cập ứng dụng

- Mở trình duyệt và truy cập: `http://localhost:8080/BT2308/`
- Chọn "Đăng nhập với Cookie" hoặc "Đăng nhập với Session"

### 3. Thông tin đăng nhập

- **Tên đăng nhập:** `admin`
- **Mật khẩu:** `password123`

### 4. Các trang có sẵn

#### Cookie Authentication:
- **Trang chủ:** `/` - Trang giới thiệu
- **Đăng nhập Cookie:** `/login` - Form đăng nhập với Cookie
- **Dashboard Cookie:** `/dashboard` - Trang chính (cần đăng nhập)
- **Hồ sơ Cookie:** `/protected/profile` - Hồ sơ cá nhân (cần đăng nhập)
- **Đăng xuất Cookie:** `/logout` - Đăng xuất khỏi hệ thống

#### Session Authentication:
- **Đăng nhập Session:** `/session-login` - Form đăng nhập với Session
- **Dashboard Session:** `/session-dashboard` - Trang chính (cần đăng nhập)
- **Hồ sơ Session:** `/session-protected/profile` - Hồ sơ cá nhân (cần đăng nhập)
- **Đăng xuất Session:** `/session-logout` - Đăng xuất khỏi hệ thống

## Cách hoạt động

### Cookie Authentication:
#### 1. Đăng nhập
- Khi người dùng đăng nhập thành công, hệ thống tạo 2 cookie:
  - `userLoggedIn=true` - Đánh dấu đã đăng nhập
  - `username=admin` - Lưu tên người dùng
- Cookie có thời hạn 30 phút

#### 2. Bảo vệ trang
- `LoginFilter` kiểm tra cookie `userLoggedIn` trước khi cho phép truy cập
- Nếu chưa đăng nhập, tự động chuyển hướng về trang đăng nhập

#### 3. Đăng xuất
- Xóa tất cả cookie liên quan đến đăng nhập
- Chuyển hướng về trang đăng nhập

### Session Authentication:
#### 1. Đăng nhập
- Khi người dùng đăng nhập thành công, hệ thống tạo session mới
- Lưu thông tin vào session attributes:
  - `userLoggedIn=true` - Đánh dấu đã đăng nhập
  - `username=admin` - Lưu tên người dùng
  - `loginTime=timestamp` - Thời gian đăng nhập
- Session có thời hạn 30 phút

#### 2. Bảo vệ trang
- `SessionFilter` kiểm tra session attribute `userLoggedIn` trước khi cho phép truy cập
- Nếu chưa đăng nhập, tự động chuyển hướng về trang đăng nhập

#### 3. Đăng xuất
- Gọi `session.invalidate()` để xóa toàn bộ session
- Chuyển hướng về trang đăng nhập

## So sánh Cookie vs Session

| Tiêu chí | Cookie | Session |
|----------|--------|---------|
| **Lưu trữ** | Client (Browser) | Server |
| **Bảo mật** | Có thể bị chỉnh sửa | An toàn hơn |
| **Dung lượng** | Giới hạn (~4KB) | Không giới hạn |
| **Hiệu suất** | Nhanh hơn | Chậm hơn một chút |
| **Hết hạn** | Theo thời gian | Theo thời gian hoặc đóng browser |
| **Phức tạp** | Đơn giản | Phức tạp hơn |

## Tùy chỉnh

### Thêm trang được bảo vệ

#### Cookie:
1. Tạo servlet mới trong thư mục `/protected/`
2. Filter sẽ tự động bảo vệ các URL có pattern `/protected/*`

#### Session:
1. Tạo servlet mới trong thư mục `/session-protected/`
2. Filter sẽ tự động bảo vệ các URL có pattern `/session-protected/*`

### Thay đổi thời gian

#### Cookie:
Trong `LoginServlet.java`, thay đổi giá trị:
```java
loginCookie.setMaxAge(30 * 60); // 30 phút
```

#### Session:
Trong `SessionLoginServlet.java`, thay đổi giá trị:
```java
session.setMaxInactiveInterval(30 * 60); // 30 phút
```

### Thêm người dùng mới

#### Cookie:
Trong `LoginServlet.java`, thêm vào phần kiểm tra đăng nhập:
```java
if ((VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) ||
    ("user2".equals(username) && "password2".equals(password))) {
    // Xử lý đăng nhập
}
```

#### Session:
Trong `SessionLoginServlet.java`, thêm vào phần kiểm tra đăng nhập:
```java
if ((VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) ||
    ("user2".equals(username) && "password2".equals(password))) {
    // Xử lý đăng nhập
}
```

## Lưu ý bảo mật

⚠️ **Đây chỉ là demo, không nên sử dụng trong production:**

- Mật khẩu được hard-code trong code
- Cookie không được mã hóa
- Không có HTTPS
- Không có CSRF protection

Để sử dụng trong production, cần:
- Lưu mật khẩu trong database với hash
- Sử dụng HTTPS
- Mã hóa cookie
- Thêm CSRF protection
- Sử dụng session thay vì cookie đơn giản

## Công nghệ sử dụng

- **Java 23**
- **Jakarta Servlet 6.1.0**
- **Maven**
- **Tomcat** (embedded)
- **HTML/CSS** (inline styles)
