import dayjs from 'dayjs/esm';

import { IPatient, NewPatient } from './patient.model';

export const sampleWithRequiredData: IPatient = {
  id: 23852,
  prenom: 'bousculer avancer',
  nom: 'pour que outre partenaire',
  sexe: 'FEMININ',
  etatCivil: 'CELIBATAIRE',
  couvertureSociale: 'NP',
  diagnostic: 'DEFICIT_COMBINE',
};

export const sampleWithPartialData: IPatient = {
  id: 30540,
  prenom: 'diplomate',
  nom: 'comme',
  sexe: 'MASCULIN',
  ageActuel: 24607,
  origine: 'MAHDIA',
  autreOrigine: 'chez',
  telephone: '+33 490235621',
  etatCivil: 'MARIE',
  couvertureSociale: 'INDIGENT',
  nomPrenomMere: 'encore à même moins',
  diagnostic: 'THROMBOPATHIE',
  dateEnregistrementRegistre: dayjs('2024-11-09'),
};

export const sampleWithFullData: IPatient = {
  id: 31693,
  prenom: 'aux alentours de essuyer',
  nom: 'gigantesque aussitôt que',
  nomJeuneFille: 'direction',
  profession: 'vraisemblablement gens très',
  sexe: 'MASCULIN',
  dateNaissance: dayjs('2024-11-09'),
  ageActuel: 29700,
  origine: 'MONASTIR',
  autreOrigine: 'vide jeune',
  adresse: 'extatique',
  telephone: '+33 692937139',
  etatCivil: 'MARIE',
  couvertureSociale: 'CNAM',
  prenomPere: 'énergique',
  professionPere: 'assez de peur de toc',
  nomPrenomMere: 'plouf lorsque',
  professionMere: 'jusque gens à la merci',
  diagnostic: 'DEFICIT_FACTEUR_VII',
  autreDiagnostic: 'devenir tôt',
  anneeDiagnostic: 28449,
  consentementRegistre: 'OUI',
  dateEnregistrementRegistre: dayjs('2024-11-09'),
};

export const sampleWithNewData: NewPatient = {
  prenom: 'avant que agréable',
  nom: 'du moment que cuicui fonctionnaire',
  sexe: 'FEMININ',
  etatCivil: 'MARIE',
  couvertureSociale: 'NP',
  diagnostic: 'THROMBOPATHIE',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
