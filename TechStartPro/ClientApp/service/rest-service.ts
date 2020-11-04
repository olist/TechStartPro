import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private _httpClient: HttpClient, @Inject('BASE_URL') private baseUrl: string) { }

  get(url: string, params?: HttpParams): Observable<any> {
    return this._httpClient.get(this.baseUrl + url, { headers: this.getHeaders(), params: params });
  }

  put(url: string, data: any, params?: HttpParams): Observable<any> {
    const body = JSON.stringify(data);
    return this._httpClient.put(this.baseUrl + url, body, { headers: this.getHeaders(), params: params });
  }

  delete(url: string, data, params?: HttpParams): Observable<any> {
    const body = JSON.stringify(data);
    return this._httpClient.delete(this.baseUrl + url + "/" + body, { headers: this.getHeaders(), params: params });
  }

  post(url: string, data: any, params?: HttpParams): Observable<any> {
    const body = JSON.stringify(data);
    return this._httpClient.post(this.baseUrl + url, body, { headers: this.getHeaders(), params: params });
  }

  private getHeaders() {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json');
    headers = headers.set('Accept', 'application/json');
    return headers;
  }

}
