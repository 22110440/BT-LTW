<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GraphQL Shop - CRUD Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        body { padding: 20px; background-color: #f8f9fa; }
        .card { margin-bottom: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .nav-pills .nav-link.active { background-color: #007bff; }
        .product-card { transition: transform 0.2s; }
        .product-card:hover { transform: translateY(-2px); }
        .price-badge { font-size: 1.1em; font-weight: bold; }
        .loading { display: none; }
        .error-message { color: #dc3545; }
        .success-message { color: #28a745; }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <h1 class="mb-4"><i class="fas fa-store"></i> GraphQL Shop Management</h1>
            
            <!-- Navigation Tabs -->
            <ul class="nav nav-pills mb-4" id="mainTabs" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="products-tab" data-bs-toggle="pill" data-bs-target="#products-panel" type="button">
                        <i class="fas fa-box"></i> Products
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="categories-tab" data-bs-toggle="pill" data-bs-target="#categories-panel" type="button">
                        <i class="fas fa-tags"></i> Categories
                    </button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="users-tab" data-bs-toggle="pill" data-bs-target="#users-panel" type="button">
                        <i class="fas fa-users"></i> Users
                    </button>
                </li>
            </ul>

            <!-- Tab Content -->
            <div class="tab-content" id="mainTabsContent">
                
                <!-- Products Panel -->
                <div class="tab-pane fade show active" id="products-panel">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <h5><i class="fas fa-list"></i> Product List</h5>
                                    <div>
                                        <button class="btn btn-outline-primary btn-sm" onclick="loadProductsByPrice()">
                                            <i class="fas fa-sort-amount-up"></i> Sort by Price
                                        </button>
                                        <div class="input-group d-inline-flex" style="width: 200px;">
                                            <input type="number" id="categoryFilter" class="form-control form-control-sm" placeholder="Category ID">
                                            <button class="btn btn-outline-secondary btn-sm" onclick="loadProductsByCategory()">Filter</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div id="products-loading" class="loading text-center">
                                        <div class="spinner-border" role="status"></div>
                                    </div>
                                    <div id="products-list" class="row"></div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5><i class="fas fa-plus"></i> Add/Edit Product</h5>
                                </div>
                                <div class="card-body">
                                    <form id="product-form">
                                        <input type="hidden" id="product-id">
                                        <div class="mb-3">
                                            <label class="form-label">Title *</label>
                                            <input type="text" class="form-control" id="product-title" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Price</label>
                                            <input type="number" step="0.01" class="form-control" id="product-price">
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Quantity</label>
                                            <input type="number" class="form-control" id="product-quantity">
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Description</label>
                                            <textarea class="form-control" id="product-desc" rows="3"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Owner ID</label>
                                            <input type="number" class="form-control" id="product-owner">
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Category IDs (comma separated)</label>
                                            <input type="text" class="form-control" id="product-categories" placeholder="1,2,3">
                                        </div>
                                        <div class="d-grid gap-2">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fas fa-save"></i> Save Product
                                            </button>
                                            <button type="button" class="btn btn-secondary" onclick="resetProductForm()">
                                                <i class="fas fa-times"></i> Cancel
                                            </button>
                                        </div>
                                    </form>
                                    <div id="product-message" class="mt-3"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Categories Panel -->
                <div class="tab-pane fade" id="categories-panel">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header">
                                    <h5><i class="fas fa-tags"></i> Categories</h5>
                                </div>
                                <div class="card-body">
                                    <div id="categories-list"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5><i class="fas fa-plus"></i> Add/Edit Category</h5>
                                </div>
                                <div class="card-body">
                                    <form id="category-form">
                                        <input type="hidden" id="category-id">
                                        <div class="mb-3">
                                            <label class="form-label">Name</label>
                                            <input type="text" class="form-control" id="category-name">
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Images URL</label>
                                            <input type="text" class="form-control" id="category-images">
                                        </div>
                                        <div class="d-grid gap-2">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fas fa-save"></i> Save Category
                                            </button>
                                            <button type="button" class="btn btn-secondary" onclick="resetCategoryForm()">
                                                <i class="fas fa-times"></i> Cancel
                                            </button>
                                        </div>
                                    </form>
                                    <div id="category-message" class="mt-3"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Users Panel -->
                <div class="tab-pane fade" id="users-panel">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="card">
                                <div class="card-header">
                                    <h5><i class="fas fa-users"></i> Users</h5>
                                </div>
                                <div class="card-body">
                                    <div id="users-list"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header">
                                    <h5><i class="fas fa-user-plus"></i> Add/Edit User</h5>
                                </div>
                                <div class="card-body">
                                    <form id="user-form">
                                        <input type="hidden" id="user-id">
                                        <div class="mb-3">
                                            <label class="form-label">Full Name *</label>
                                            <input type="text" class="form-control" id="user-fullname" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Email *</label>
                                            <input type="email" class="form-control" id="user-email" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Password *</label>
                                            <input type="password" class="form-control" id="user-password" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Phone</label>
                                            <input type="text" class="form-control" id="user-phone">
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Category IDs (comma separated)</label>
                                            <input type="text" class="form-control" id="user-categories" placeholder="1,2,3">
                                        </div>
                                        <div class="d-grid gap-2">
                                            <button type="submit" class="btn btn-primary">
                                                <i class="fas fa-save"></i> Save User
                                            </button>
                                            <button type="button" class="btn btn-secondary" onclick="resetUserForm()">
                                                <i class="fas fa-times"></i> Cancel
                                            </button>
                                        </div>
                                    </form>
                                    <div id="user-message" class="mt-3"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    const GQL_ENDPOINT = "/graphql";

    // GraphQL helper function
    async function gql(query, variables = {}) {
        try {
            const response = await fetch(GQL_ENDPOINT, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ query, variables })
            });
            const result = await response.json();
            if (result.errors) {
                console.error("GraphQL errors:", result.errors);
                throw new Error(result.errors[0].message);
            }
            return result;
        } catch (error) {
            console.error("GraphQL request failed:", error);
            throw error;
        }
    }

    // Show message helper
    function showMessage(elementId, message, isError = false) {
        const element = document.getElementById(elementId);
        element.innerHTML = `<div class="alert alert-${isError ? 'danger' : 'success'} alert-dismissible fade show">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>`;
    }

    // ===== PRODUCTS =====
    
    async function loadProducts() {
        document.getElementById('products-loading').style.display = 'block';
        try {
            const query = `
                query {
                    products {
                        id title price quantity desc
                        owner { id fullname }
                        categories { id name }
                    }
                }`;
            const { data } = await gql(query);
            displayProducts(data.products);
        } catch (error) {
            showMessage('products-list', 'Error loading products: ' + error.message, true);
        } finally {
            document.getElementById('products-loading').style.display = 'none';
        }
    }

    async function loadProductsByPrice() {
        document.getElementById('products-loading').style.display = 'block';
        try {
            const query = `
                query {
                    productsByPriceAsc {
                        id title price quantity desc
                        owner { id fullname }
                        categories { id name }
                    }
                }`;
            const { data } = await gql(query);
            displayProducts(data.productsByPriceAsc);
        } catch (error) {
            showMessage('products-list', 'Error loading products by price: ' + error.message, true);
        } finally {
            document.getElementById('products-loading').style.display = 'none';
        }
    }

    async function loadProductsByCategory() {
        const categoryId = document.getElementById('categoryFilter').value;
        if (!categoryId) {
            alert('Please enter a category ID');
            return;
        }
        
        document.getElementById('products-loading').style.display = 'block';
        try {
            const query = `
                query($categoryId: ID!) {
                    productsByCategory(categoryId: $categoryId) {
                        id title price quantity desc
                        owner { id fullname }
                        categories { id name }
                    }
                }`;
            const { data } = await gql(query, { categoryId });
            displayProducts(data.productsByCategory);
        } catch (error) {
            showMessage('products-list', 'Error loading products by category: ' + error.message, true);
        } finally {
            document.getElementById('products-loading').style.display = 'none';
        }
    }

    function displayProducts(products) {
        const container = document.getElementById('products-list');
        if (!products || products.length === 0) {
            container.innerHTML = '<div class="col-12"><div class="alert alert-info">No products found</div></div>';
            return;
        }

        container.innerHTML = products.map(product => `
            <div class="col-md-6 mb-3">
                <div class="card product-card h-100">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <h6 class="card-title mb-0">${product.title}</h6>
                            <span class="badge bg-primary price-badge">$${product.price || 0}</span>
                        </div>
                        <p class="card-text small text-muted">${product.desc || 'No description'}</p>
                        <div class="row text-center">
                            <div class="col-6">
                                <small class="text-muted">Quantity</small><br>
                                <strong>${product.quantity || 0}</strong>
                            </div>
                            <div class="col-6">
                                <small class="text-muted">Owner</small><br>
                                <strong>${product.owner?.fullname || 'N/A'}</strong>
                            </div>
                        </div>
                        ${product.categories && product.categories.length > 0 ? `
                            <div class="mt-2">
                                ${product.categories.map(cat => `<span class="badge bg-secondary me-1">${cat.name}</span>`).join('')}
                            </div>
                        ` : ''}
                        <div class="mt-3">
                            <button class="btn btn-sm btn-outline-primary" onclick="editProduct(${product.id})">
                                <i class="fas fa-edit"></i> Edit
                            </button>
                            <button class="btn btn-sm btn-outline-danger" onclick="deleteProduct(${product.id})">
                                <i class="fas fa-trash"></i> Delete
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        `).join('');
    }

    async function editProduct(id) {
        try {
            const query = `
                query($id: ID!) {
                    product(id: $id) {
                        id title price quantity desc
                        owner { id }
                        categories { id }
                    }
                }`;
            const { data } = await gql(query, { id });
            const product = data.product;
            
            document.getElementById('product-id').value = product.id;
            document.getElementById('product-title').value = product.title || '';
            document.getElementById('product-price').value = product.price || '';
            document.getElementById('product-quantity').value = product.quantity || '';
            document.getElementById('product-desc').value = product.desc || '';
            document.getElementById('product-owner').value = product.owner?.id || '';
            document.getElementById('product-categories').value = product.categories?.map(c => c.id).join(',') || '';
        } catch (error) {
            showMessage('product-message', 'Error loading product: ' + error.message, true);
        }
    }

    async function deleteProduct(id) {
        if (!confirm('Are you sure you want to delete this product?')) return;
        
        try {
            const mutation = `
                mutation($id: ID!) {
                    deleteProduct(id: $id)
                }`;
            await gql(mutation, { id });
            showMessage('product-message', 'Product deleted successfully!');
            loadProducts();
        } catch (error) {
            showMessage('product-message', 'Error deleting product: ' + error.message, true);
        }
    }

    function resetProductForm() {
        document.getElementById('product-form').reset();
        document.getElementById('product-id').value = '';
        document.getElementById('product-message').innerHTML = '';
    }

    document.getElementById('product-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const id = document.getElementById('product-id').value;
        const title = document.getElementById('product-title').value;
        const price = parseFloat(document.getElementById('product-price').value) || null;
        const quantity = parseInt(document.getElementById('product-quantity').value) || null;
        const desc = document.getElementById('product-desc').value || null;
        const ownerId = parseInt(document.getElementById('product-owner').value) || null;
        const categoryIds = document.getElementById('product-categories').value
            .split(',').map(id => parseInt(id.trim())).filter(id => !isNaN(id));

        try {
            if (id) {
                // Update
                const mutation = `
                    mutation($input: UpdateProductInput!) {
                        updateProduct(input: $input) {
                            id title
                        }
                    }`;
                await gql(mutation, {
                    input: { id: parseInt(id), title, price, quantity, desc, ownerId, categoryIds }
                });
                showMessage('product-message', 'Product updated successfully!');
            } else {
                // Create
                const mutation = `
                    mutation($input: CreateProductInput!) {
                        createProduct(input: $input) {
                            id title
                        }
                    }`;
                await gql(mutation, {
                    input: { title, price, quantity, desc, ownerId, categoryIds }
                });
                showMessage('product-message', 'Product created successfully!');
            }
            resetProductForm();
            loadProducts();
        } catch (error) {
            showMessage('product-message', 'Error saving product: ' + error.message, true);
        }
    });

    // ===== CATEGORIES =====
    
    async function loadCategories() {
        try {
            const query = `
                query {
                    categories {
                        id name images
                        products { id title }
                    }
                }`;
            const { data } = await gql(query);
            displayCategories(data.categories);
        } catch (error) {
            showMessage('categories-list', 'Error loading categories: ' + error.message, true);
        }
    }

    function displayCategories(categories) {
        const container = document.getElementById('categories-list');
        if (!categories || categories.length === 0) {
            container.innerHTML = '<div class="alert alert-info">No categories found</div>';
            return;
        }

        container.innerHTML = `
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Images</th>
                            <th>Products Count</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${categories.map(category => `
                            <tr>
                                <td>${category.id}</td>
                                <td>${category.name || 'N/A'}</td>
                                <td>${category.images ? `<img src="${category.images}" style="width:50px;height:50px;object-fit:cover;" onerror="this.style.display='none'">` : 'N/A'}</td>
                                <td><span class="badge bg-info">${category.products?.length || 0}</span></td>
                                <td>
                                    <button class="btn btn-sm btn-outline-primary" onclick="editCategory(${category.id})">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-sm btn-outline-danger" onclick="deleteCategory(${category.id})">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        `;
    }

    async function editCategory(id) {
        try {
            const query = `
                query($id: ID!) {
                    category(id: $id) {
                        id name images
                    }
                }`;
            const { data } = await gql(query, { id });
            const category = data.category;
            
            document.getElementById('category-id').value = category.id;
            document.getElementById('category-name').value = category.name || '';
            document.getElementById('category-images').value = category.images || '';
        } catch (error) {
            showMessage('category-message', 'Error loading category: ' + error.message, true);
        }
    }

    async function deleteCategory(id) {
        if (!confirm('Are you sure you want to delete this category?')) return;
        
        try {
            const mutation = `
                mutation($id: ID!) {
                    deleteCategory(id: $id)
                }`;
            await gql(mutation, { id });
            showMessage('category-message', 'Category deleted successfully!');
            loadCategories();
        } catch (error) {
            showMessage('category-message', 'Error deleting category: ' + error.message, true);
        }
    }

    function resetCategoryForm() {
        document.getElementById('category-form').reset();
        document.getElementById('category-id').value = '';
        document.getElementById('category-message').innerHTML = '';
    }

    document.getElementById('category-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const id = document.getElementById('category-id').value;
        const name = document.getElementById('category-name').value || null;
        const images = document.getElementById('category-images').value || null;

        try {
            if (id) {
                // Update
                const mutation = `
                    mutation($input: UpdateCategoryInput!) {
                        updateCategory(input: $input) {
                            id name
                        }
                    }`;
                await gql(mutation, {
                    input: { id: parseInt(id), name, images }
                });
                showMessage('category-message', 'Category updated successfully!');
            } else {
                // Create
                const mutation = `
                    mutation($input: CreateCategoryInput!) {
                        createCategory(input: $input) {
                            id name
                        }
                    }`;
                await gql(mutation, {
                    input: { name, images }
                });
                showMessage('category-message', 'Category created successfully!');
            }
            resetCategoryForm();
            loadCategories();
        } catch (error) {
            showMessage('category-message', 'Error saving category: ' + error.message, true);
        }
    });

    // ===== USERS =====
    
    async function loadUsers() {
        try {
            const query = `
                query {
                    users {
                        id fullname email phone
                        categories { id name }
                    }
                }`;
            const { data } = await gql(query);
            displayUsers(data.users);
        } catch (error) {
            showMessage('users-list', 'Error loading users: ' + error.message, true);
        }
    }

    function displayUsers(users) {
        const container = document.getElementById('users-list');
        if (!users || users.length === 0) {
            container.innerHTML = '<div class="alert alert-info">No users found</div>';
            return;
        }

        container.innerHTML = `
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Categories</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${users.map(user => `
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.fullname}</td>
                                <td>${user.email}</td>
                                <td>${user.phone || 'N/A'}</td>
                                <td>
                                    ${user.categories && user.categories.length > 0 
                                        ? user.categories.map(cat => `<span class="badge bg-secondary me-1">${cat.name}</span>`).join('')
                                        : 'None'}
                                </td>
                                <td>
                                    <button class="btn btn-sm btn-outline-primary" onclick="editUser(${user.id})">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-sm btn-outline-danger" onclick="deleteUser(${user.id})">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        `;
    }

    async function editUser(id) {
        try {
            const query = `
                query($id: ID!) {
                    user(id: $id) {
                        id fullname email phone
                        categories { id }
                    }
                }`;
            const { data } = await gql(query, { id });
            const user = data.user;
            
            document.getElementById('user-id').value = user.id;
            document.getElementById('user-fullname').value = user.fullname || '';
            document.getElementById('user-email').value = user.email || '';
            document.getElementById('user-password').value = ''; // Don't populate password
            document.getElementById('user-phone').value = user.phone || '';
            document.getElementById('user-categories').value = user.categories?.map(c => c.id).join(',') || '';
        } catch (error) {
            showMessage('user-message', 'Error loading user: ' + error.message, true);
        }
    }

    async function deleteUser(id) {
        if (!confirm('Are you sure you want to delete this user?')) return;
        
        try {
            const mutation = `
                mutation($id: ID!) {
                    deleteUser(id: $id)
                }`;
            await gql(mutation, { id });
            showMessage('user-message', 'User deleted successfully!');
            loadUsers();
        } catch (error) {
            showMessage('user-message', 'Error deleting user: ' + error.message, true);
        }
    }

    function resetUserForm() {
        document.getElementById('user-form').reset();
        document.getElementById('user-id').value = '';
        document.getElementById('user-message').innerHTML = '';
    }

    document.getElementById('user-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const id = document.getElementById('user-id').value;
        const fullname = document.getElementById('user-fullname').value;
        const email = document.getElementById('user-email').value;
        const password = document.getElementById('user-password').value;
        const phone = document.getElementById('user-phone').value || null;
        const categoryIds = document.getElementById('user-categories').value
            .split(',').map(id => parseInt(id.trim())).filter(id => !isNaN(id));

        try {
            if (id) {
                // Update
                const mutation = `
                    mutation($input: UpdateUserInput!) {
                        updateUser(input: $input) {
                            id fullname
                        }
                    }`;
                const input = { id: parseInt(id), fullname, email, phone, categoryIds };
                if (password) input.password = password; // Only update password if provided
                
                await gql(mutation, { input });
                showMessage('user-message', 'User updated successfully!');
            } else {
                // Create
                const mutation = `
                    mutation($input: CreateUserInput!) {
                        createUser(input: $input) {
                            id fullname
                        }
                    }`;
                await gql(mutation, {
                    input: { fullname, email, password, phone, categoryIds }
                });
                showMessage('user-message', 'User created successfully!');
            }
            resetUserForm();
            loadUsers();
        } catch (error) {
            showMessage('user-message', 'Error saving user: ' + error.message, true);
        }
    });

    // ===== INITIALIZATION =====
    
    // Load data when tabs are shown
    document.getElementById('products-tab').addEventListener('shown.bs.tab', loadProducts);
    document.getElementById('categories-tab').addEventListener('shown.bs.tab', loadCategories);
    document.getElementById('users-tab').addEventListener('shown.bs.tab', loadUsers);

    // Initial load
    document.addEventListener('DOMContentLoaded', () => {
        loadProducts();
    });
</script>
</body>
</html>
