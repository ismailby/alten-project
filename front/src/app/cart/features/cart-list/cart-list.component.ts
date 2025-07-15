import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { CartItem } from 'app/cart/data-access/cart.model';
import { CartService } from 'app/cart/data-access/cart.service';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
@Component({
  selector: 'app-cart-list',
  standalone: true,
  imports: [CommonModule,
    ButtonModule,TableModule],
  templateUrl: './cart-list.component.html',
  styleUrl: './cart-list.component.css'
})
export class CartListComponent {
cartItems: CartItem[] = [];
private readonly cartService = inject(CartService);
  
ngOnInit(){
  this.loadCart();
}
loadCart(): void {
    this.cartService.getCart().subscribe({
      next: (items) => (this.cartItems = items),
      error: (err) => console.error('Erreur lors du chargement du panier', err),
    });
  }
 removeItem(productId: number) {
    this.cartService.removeFromCart(productId).subscribe({
      next: () => {
        this.cartItems = this.cartItems.filter(item => item.product.id !== productId);
      },
      error: () => alert('Erreur lors de la suppression')
    });
  }

  clearCart(){
      this.cartService.clearCart().subscribe({
      next: () => {
        this.cartItems = [];
      },
      error: () => alert('Erreur lors de la suppression')
    });
  }
}
