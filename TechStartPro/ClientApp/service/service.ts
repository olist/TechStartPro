import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private _httpClient: HttpClient) { }

    get(url: string, params?: HttpParams): Observable<any> {
        return this._httpClient.get(environment.BUG_FINDER_API + url, { headers: this.getHeaders(), params: params });
    }

    put(url: string, data: any, params?: HttpParams): Observable<any> {
        const body = JSON.stringify(data);
        return this._httpClient.put(environment.BUG_FINDER_API + url, body, { headers: this.getHeaders(), params: params });
    }

    delete(url: string, params?: HttpParams): Observable<any> {
        return this._httpClient.delete(environment.BUG_FINDER_API + url, { headers: this.getHeaders(), params: params });
    }

    post(url: string, data: any, params?: HttpParams): Observable<any> {
        const body = JSON.stringify(data);
        return this._httpClient.post(environment.BUG_FINDER_API + url, body, { headers: this.getHeaders(), params: params });
    }

    private getHeaders() {
        let headers = new HttpHeaders();
        headers = headers.set('Content-Type', 'application/json');
        headers = headers.set('Accept', 'application/json');
        return headers;
    }

}