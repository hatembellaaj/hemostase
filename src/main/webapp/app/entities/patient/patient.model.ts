import dayjs from 'dayjs/esm';
import { Gender } from 'app/entities/enumerations/gender.model';
import { Region } from 'app/entities/enumerations/region.model';
import { CivilStatus } from 'app/entities/enumerations/civil-status.model';
import { CouvertureSociale } from 'app/entities/enumerations/couverture-sociale.model';
import { DiagnosticType } from 'app/entities/enumerations/diagnostic-type.model';
import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';

export interface IPatient {
  id: number;
  prenom?: string | null;
  nom?: string | null;
  nomJeuneFille?: string | null;
  profession?: string | null;
  sexe?: keyof typeof Gender | null;
  dateNaissance?: dayjs.Dayjs | null;
  ageActuel?: number | null;
  origine?: keyof typeof Region | null;
  autreOrigine?: string | null;
  adresse?: string | null;
  telephone?: string | null;
  etatCivil?: keyof typeof CivilStatus | null;
  couvertureSociale?: keyof typeof CouvertureSociale | null;
  prenomPere?: string | null;
  professionPere?: string | null;
  nomPrenomMere?: string | null;
  professionMere?: string | null;
  diagnostic?: keyof typeof DiagnosticType | null;
  autreDiagnostic?: string | null;
  anneeDiagnostic?: number | null;
  consentementRegistre?: keyof typeof OuiNonNP | null;
  dateEnregistrementRegistre?: dayjs.Dayjs | null;
}

export type NewPatient = Omit<IPatient, 'id'> & { id: null };
