import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 6077,
  login: 'MHMMr4',
};

export const sampleWithPartialData: IUser = {
  id: 15895,
  login: '9',
};

export const sampleWithFullData: IUser = {
  id: 25789,
  login: 'jCS.S',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
