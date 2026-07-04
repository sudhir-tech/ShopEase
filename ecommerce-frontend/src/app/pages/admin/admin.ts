import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ProductService } from '../../services/product';
import { Product } from '../../models/product';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css'
})
export class Admin implements OnInit {

  products: Product[] = [];

  product: Product = {
  name: '',
  description: '',
  price: 0,
  stock: 0
};

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }

  saveProduct() {

  // Update existing product
  if (this.product.id) {

    this.productService.updateProduct(this.product.id, this.product).subscribe({

      next: () => {

        alert("Product updated successfully");

        this.product = {
          name: '',
          description: '',
          price: 0,
          stock: 0
        };

        this.loadProducts();

      },

      error: (err) => {

        console.log(err);

        alert("Failed to update product");

      }

    });

    return;
  }

  // Add new product
  this.productService.addProduct(this.product).subscribe({

    next: () => {

      alert("Product added successfully");

      this.product = {
        name: '',
        description: '',
        price: 0,
        stock: 0
      };

      this.loadProducts();

    },

    error: (err) => {

      console.log(err);

      alert("Failed to add product");

    }

  });

}

  editProduct(product: Product) {

  this.product = {
    id: product.id,
    name: product.name,
    description: product.description,
    price: product.price,
    stock: product.stock
  };

}

  deleteProduct(id: number) {

  if (!confirm("Delete this product?")) {
    return;
  }

  this.productService.deleteProduct(id).subscribe({

    next: () => {

      alert("Product deleted successfully");

      this.loadProducts();

    },

    error: (err) => {

      console.log(err);

      alert("Failed to delete product");

    }
    

  });

}

}