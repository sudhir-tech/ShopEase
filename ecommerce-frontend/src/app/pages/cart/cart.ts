import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartService } from '../../services/cart';
import { CartResponse } from '../../models/cart-response';

@Component({
  selector: 'app-cart',
  imports: [CommonModule],
   standalone: true,
  templateUrl: './cart.html',
  styleUrl: './cart.css'
})
export class Cart implements OnInit {

  cartItems: CartResponse[] = [];

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.loadCart();
  }

  loadCart() {

  console.log("Cart component loaded");

  this.cartService.getCart(1).subscribe({

    next: (data) => {

      console.log(data);

      this.cartItems = data;

    },

    error: (err) => {

      console.log(err);

    }

  });

}

  remove(cartItemId: number) {
    this.cartService.remove(cartItemId).subscribe({
      next: (response) => {
        alert(response);
        this.loadCart();
      }
    });
  }

  getTotalPrice(): number {

    return this.cartItems.reduce((sum, item) =>

      sum + (item.price * item.quantity), 0);

  }

  placeOrder() {

    this.cartService.placeOrder(1).subscribe({

      next: (response) => {

        alert(response);

        this.loadCart();

      }

    });

  }

}