<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-success">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <i class="bi bi-play-circle-fill me-2"></i>
                Video Management System
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="index.jsp">
                    <i class="bi bi-house me-1"></i>Trang chủ
                </a>
                <a class="nav-link" href="users.html">
                    <i class="bi bi-people me-1"></i>Người dùng
                </a>
                <a class="nav-link active" href="categories.html">
                    <i class="bi bi-collection me-1"></i>Danh mục
                </a>
                <a class="nav-link" href="videos.html">
                    <i class="bi bi-play-btn me-1"></i>Video
                </a>
            </div>
        </div>
    </nav>

    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="text-success">
                <i class="bi bi-collection-fill me-2"></i>
                Quản lý Danh mục
            </h1>
            <button class="btn btn-outline-success" onclick="document.getElementById('formCategory').reset(); document.getElementById('id').value='';">
                <i class="bi bi-plus-circle me-1"></i>Thêm mới
            </button>
        </div>

        <div class="card shadow-sm mb-4">
            <div class="card-header bg-success text-white">
                <h5 class="mb-0">
                    <i class="bi bi-folder-plus me-2"></i>
                    Thông tin Danh mục
                </h5>
            </div>
            <div class="card-body">
                <form id="formCategory">
                    <input type="hidden" id="id">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label class="form-label fw-bold">Tên danh mục</label>
                                <input type="text" id="name" class="form-control" required placeholder="Nhập tên danh mục">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label class="form-label fw-bold">Mô tả</label>
                                <textarea id="description" class="form-control" rows="3" placeholder="Nhập mô tả danh mục"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="text-end">
                        <button type="submit" class="btn btn-success">
                            <i class="bi bi-check-circle me-1"></i>Lưu
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="card shadow-sm">
            <div class="card-header bg-light">
                <div class="d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">
                        <i class="bi bi-search me-2"></i>
                        Danh sách Danh mục
                    </h5>
                    <div class="input-group" style="width: 300px;">
                        <span class="input-group-text">
                            <i class="bi bi-search"></i>
                        </span>
                        <input type="text" id="search" class="form-control" placeholder="Tìm kiếm theo tên...">
                    </div>
                </div>
            </div>
            <div class="card-body p-0">

                <table class="table table-hover mb-0">
                    <thead class="table-success">
                        <tr>
                            <th>ID</th>
                            <th>Tên danh mục</th>
                            <th>Mô tả</th>
                            <th class="text-center">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody id="tblCategories"></tbody>
                </table>
            </div>
        </div>
    </div>

<script>
    const API = '/admin/categories';

    async function loadCategories() {
        const res = await fetch(API);
        const data = await res.json();
        render(data);
    }

    function render(data) {
        const tbody = document.getElementById('tblCategories');
        tbody.innerHTML = '';
        data.forEach(c => {
            tbody.innerHTML += `
      <tr>
        <td>${c.id}</td>
        <td>
          <i class="bi bi-folder me-2"></i>
          ${c.name}
        </td>
        <td>${c.description||'<em class="text-muted">Không có mô tả</em>'}</td>
        <td class="text-center">
          <button class="btn btn-sm btn-warning me-1" onclick="edit(${c.id})" title="Chỉnh sửa">
            <i class="bi bi-pencil"></i>
          </button>
          <button class="btn btn-sm btn-danger" onclick="remove(${c.id})" title="Xóa">
            <i class="bi bi-trash"></i>
          </button>
        </td>
      </tr>`;
        });
    }

    document.getElementById('formCategory').onsubmit = async (e) => {
        e.preventDefault();
        const id = document.getElementById('id').value;
        const payload = {
            name: document.getElementById('name').value,
            description: document.getElementById('description').value
        };
        await fetch(id ? API+'/'+id : API, {
            method: id ? 'PUT' : 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(payload)
        });
        e.target.reset();
        document.getElementById('id').value='';
        loadCategories();
    };

    async function edit(id) {
        const res = await fetch(API+'/'+id);
        const c = await res.json();
        document.getElementById('id').value = c.id;
        document.getElementById('name').value = c.name;
        document.getElementById('description').value = c.description;
    }

    async function remove(id) {
        if(confirm('Bạn có chắc chắn muốn xóa danh mục này?')) {
            await fetch(API+'/'+id,{method:'DELETE'});
            loadCategories();
        }
    }

    document.getElementById('search').oninput = async (e) => {
        const q = e.target.value;
        if(!q) return loadCategories();
        const res = await fetch(API+'/search?keyword='+encodeURIComponent(q));
        render(await res.json());
    };

    loadCategories();
</script>
</body>
</html>
