// cart-item.model.ts
export interface CartItem {
  id: number;
  user: {
    id: number;
    username: string;
    firstname: string;
    email: string;
    password: string;
  };
  product: {
    id: number;
    code: string;
    name: string;
    description: string;
    image: string;
    category: string;
    price: number;
    quantity: number;
    internalReference: string;
    shellId: number;
    inventoryStatus: string;
    rating: number;
    createdAt: number;
    updatedAt: number;
  };
  quantity: number;
}
