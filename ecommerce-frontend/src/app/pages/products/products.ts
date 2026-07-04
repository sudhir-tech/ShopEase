import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { CartService } from '../../services/cart';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './products.html',
  styleUrl: './products.css'
})

export class Products implements OnInit {

  products: Product[] = [];
  searchText = '';

  constructor(
    private productService: ProductService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
      console.log(this.products);
    });
  }
  searchProducts() {

  if (this.searchText.trim() === '') {

    this.loadProducts();
    return;

  }

  this.productService.searchProducts(this.searchText).subscribe({

    next: (data) => {

      this.products = data;

    }

  });

}
  

  addToCart(productId: number) {

  const dto = {
    userId: 1,
    productId: productId,
    quantity: 1
  };

  this.cartService.addToCart(dto).subscribe({
    next: (response) => {
      alert(response);
    }
  });
}

}