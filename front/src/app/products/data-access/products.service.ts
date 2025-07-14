import { Injectable, inject, signal } from "@angular/core";
import { Product } from "./product.model";
import { HttpClient } from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";
import { environment } from "environments/environment";

@Injectable({
    providedIn: "root"
}) export class ProductsService {

    private readonly http = inject(HttpClient);
     private readonly urlProduct = environment.baseUrl+'/products';
    private readonly _products = signal<Product[]>([]);

    public readonly products = this._products.asReadonly();

    public get(): Observable<Product[]> {
        return this.http.get<Product[]>(this.urlProduct).pipe(
            catchError((error) => {
                return this.http.get<Product[]>(this.urlProduct);
            }),
            tap((products) => this._products.set(products)),
        );
    }
     
    public create(product: Product): Observable<boolean> {
        return this.http.post<boolean>(this.urlProduct, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => [product, ...products])),
        );
    }

    public update(product: Product): Observable<boolean> {
        return this.http.put<boolean>(`${this.urlProduct}/${product.id}`, product).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => {
                return products.map(p => p.id === product.id ? product : p)
            })),
        );
    }

    public delete(productId: number): Observable<boolean> {
        return this.http.delete<boolean>(`${this.urlProduct}/${productId}`).pipe(
            catchError(() => {
                return of(true);
            }),
            tap(() => this._products.update(products => products.filter(product => product.id !== productId))),
        );
    }
}