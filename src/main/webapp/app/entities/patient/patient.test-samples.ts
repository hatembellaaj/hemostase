import dayjs from 'dayjs/esm';

import { IPatient, NewPatient } from './patient.model';

export const sampleWithRequiredData: IPatient = {
  id: 22594,
  prenom: 'diviser',
  nom: 'de par',
  sexe: 'MASCULIN',
  etatCivil: 'NP',
  couvertureSociale: 'INDIGENT',
  diagnostic: 'HEMOPHILIE',
};

export const sampleWithPartialData: IPatient = {
  id: 28712,
  prenom: 'membre titulaire',
  nom: 'sincère comment',
  sexe: 'FEMININ',
  ageActuel: 3966,
  autreOrigine: 'solitaire quand ?',
  adresse: 'cot cot trop perplexe',
  etatCivil: 'DIVORCE',
  couvertureSociale: 'CNAM',
  professionPere: 'séculaire',
  professionMere: 'un peu éloigner multiplier',
  medecinTraitant: 'magnifique solitaire durant',
  biologisteResponsableDg: 'amorphe compromettre de manièr',
  diagnostic: 'HEMOPHILIE',
  autreDiagnostic: 'pin-pon maigre crac',
  anneeDiagnostic: 13190,
  consentementRegistre: 'OUI',
  dateEnregistrementRegistre: dayjs('2024-11-30'),
};

export const sampleWithFullData: IPatient = {
  id: 11150,
  prenom: 'en faveur de',
  nom: 'horrible foule triangulaire',
  nomJeuneFille: 'badaboum derrière de façon à',
  profession: 'autrefois égarer',
  sexe: 'FEMININ',
  dateNaissance: dayjs('2024-11-29'),
  ageActuel: 15759,
  origine: 'MEDNINE',
  autreOrigine: 'extra ha dans',
  adresse: 'ouin au-dehors agir',
  telephone: '0495066238',
  etatCivil: 'DIVORCE',
  couvertureSociale: 'NP',
  prenomPere: 'rectorat lectorat aussitôt que',
  professionPere: 'pourpre tchou tchouu insolite',
  nomPrenomMere: 'représenter',
  professionMere: 'pendant que passager',
  medecinTraitant: 'foncer',
  serviceOrigine: 'diététiste',
  biologisteResponsableDg: 'pour',
  diagnostic: 'DEFICIT_COMBINE',
  autreDiagnostic: 'vu que main-d’œuvre',
  anneeDiagnostic: 13028,
  consentementRegistre: 'NON',
  dateEnregistrementRegistre: dayjs('2024-11-30'),
};

export const sampleWithNewData: NewPatient = {
  prenom: 'mairie corps enseignant athlèt',
  nom: 'équipe soit',
  sexe: 'MASCULIN',
  etatCivil: 'CELIBATAIRE',
  couvertureSociale: 'NP',
  diagnostic: 'AUTRE',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
