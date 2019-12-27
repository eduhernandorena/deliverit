import {environment} from '../../environments/environment';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConnectService {

  constructor(
    protected http: HttpClient
  ) {
  }

  getGenericAll(path: string) {
    return new Promise<any>((resolve, reject) => {
      console.log(environment.urlConnect + path + '/');
      this.http.get(environment.urlConnect + path + '/').subscribe(res => {
        resolve(res as any);
      }, erro => {
        reject(erro as any);
      });
    });
  }

  getGenericId(path: string, id: any) {
    return new Promise<any>((resolve, reject) => {
      this.http.get(environment.urlConnect + path + '/' + id).subscribe(res => {
        resolve(res as any);
      }, erro => {
        reject(erro as any);
      });
    });
  }

  postGeneric(path: string, object: any) {
    console.log('POST: ', object);
    return new Promise<any>((resolve, reject) => {
      this.http.post(environment.urlConnect + path + '/', object).subscribe(res => {
        resolve(res as any);
      }, erro => {
        reject(erro as any);
      });
    });
  }

  putGeneric(path: string, object: any) {
    console.log('PUT: ', object);
    return new Promise<any>((resolve, reject) => {
      this.http.put(environment.urlConnect + path, object).subscribe(res => {
        resolve(res as any);
      }, erro => {
        reject(erro as any);
      });
    });
  }

  deleteGeneric(path: string, id: any) {
    return new Promise<any>((resolve, reject) => {
      this.http.delete(environment.urlConnect + path + '/' + id).subscribe(res => {
        resolve(res as any);
      }, erro => {
        reject(erro as any);
      });
    });
  }
}
