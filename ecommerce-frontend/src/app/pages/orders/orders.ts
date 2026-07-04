import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderService } from '../../services/order';
import { Order } from '../../models/order';

@Component({
  selector: 'app-orders',
  imports: [CommonModule],
   standalone: true,
  templateUrl: './orders.html',
  styleUrl: './orders.css'
})
export class Orders implements OnInit {

  orders: Order[] = [];

  constructor(private orderService: OrderService) {}

 ngOnInit(): void {

    console.log("Orders Component Loaded");

    this.loadOrders();

}

  loadOrders() {

  this.orderService.getOrders(1).subscribe({

    next: (data) => {

      console.log("Orders received:", data);

      this.orders = data;

    },

    error: (err) => {

      console.log("Error:", err);

    }

  });

}

}