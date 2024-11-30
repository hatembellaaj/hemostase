import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 31091,
  login: 'R6z0',
};

export const sampleWithPartialData: IUser = {
  id: 22806,
  login: 'pVD1Wj',
};

export const sampleWithFullData: IUser = {
  id: 24242,
  login: 'CB',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
