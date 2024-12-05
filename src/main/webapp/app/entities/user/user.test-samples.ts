import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 9726,
  login: 'BZ',
};

export const sampleWithPartialData: IUser = {
  id: 13636,
  login: 'nD@ouwmig\\:s\\)MT',
};

export const sampleWithFullData: IUser = {
  id: 27173,
  login: 'f@agn2\\ds16l\\qinyX\\456wwk\\nUH\\7lw38UR',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
