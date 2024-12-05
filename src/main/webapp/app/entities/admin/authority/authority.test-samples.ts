import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '81f2adca-57eb-423c-b4e7-895691fcd61b',
};

export const sampleWithPartialData: IAuthority = {
  name: '86c720f8-7f4e-4e62-b873-4ca36b0bb06f',
};

export const sampleWithFullData: IAuthority = {
  name: 'e13cc4f2-4752-4bde-a96a-46cbe4a43ae5',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
