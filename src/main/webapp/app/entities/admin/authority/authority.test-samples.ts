import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '02b7141b-737c-40fc-ae45-3b1760fe02b4',
};

export const sampleWithPartialData: IAuthority = {
  name: '1ef0fcdf-94b8-466f-b764-2b2330c67afc',
};

export const sampleWithFullData: IAuthority = {
  name: '26cb8662-6d99-4b5e-9284-8b97ab677285',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
