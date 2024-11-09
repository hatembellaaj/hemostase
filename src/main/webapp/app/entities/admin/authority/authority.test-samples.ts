import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: 'd9935e5e-020a-4d5f-b217-a2d350404e74',
};

export const sampleWithPartialData: IAuthority = {
  name: 'fe4a6619-0cf3-44aa-947f-eaac1a4d5c6d',
};

export const sampleWithFullData: IAuthority = {
  name: 'a334dd33-e145-4379-963a-16de0c5b7ccd',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
