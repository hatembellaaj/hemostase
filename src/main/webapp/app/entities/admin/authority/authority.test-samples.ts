import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'aaf46078-abe7-4c9b-9d9e-227bdac5d58a',
};

export const sampleWithPartialData: IAuthority = {
  name: '51c5ce17-26af-4e53-81a4-eaae79243eb9',
};

export const sampleWithFullData: IAuthority = {
  name: 'd79ed48b-72ba-4d32-abc0-89d3161c52ce',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
