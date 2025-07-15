import { Component, OnInit, ViewChild, inject, signal } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { CartService } from "app/cart/data-access/cart.service";
import { Product } from "app/products/data-access/product.model";
import { ProductsService } from "app/products/data-access/products.service";
import { ProductFormComponent } from "app/products/ui/product-form/product-form.component";
import { TokenStorageService } from "app/services/token-storage.service";
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';

const emptyProduct: Product = {
  id: 0,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: 0,
  updatedAt: 0,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent,FormsModule],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly cartService=inject(CartService);
   @ViewChild(ProductFormComponent)
  productForm!: ProductFormComponent;
quantities: { [productId: number]: number } = {};
  public readonly products = this.productsService.products;

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);
constructor (private tokenStorage:TokenStorageService){}
  ngOnInit() {
   //   const token = this.tokenStorage.saveToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJpYXQiOjE3NTI1OTQzMDcsImV4cCI6MTc1MjU5NTIwN30.0kXoBSkGxeGZ3wmG3kmYDcvMnX2Xtu7ydo_TsLCLUgw");
    this.productsService.get().subscribe();
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    this.productsService.delete(product.id).subscribe();
  }

  public onSave(product: Product) {
    if (this.isCreation) {
      this.productsService.create(product).subscribe({
  next: (res) => {
    this.isCreation=true;
  }
});
    } else {
      this.productsService.update(product).subscribe();
    }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  addToCart(productId: number) {
    const quantity = this.quantities[productId] || 1;
    console.log("test")
  this.cartService.addToCart(productId, quantity).subscribe({
  next: (res) => {
    console.log('Réponse OK :', res);
    alert('Produit ajouté au panier');
  },
  error: (err) => {
    console.error('Erreur :', err);
    alert('Erreur lors de l’ajout au panier');
  }
});
  }

}
