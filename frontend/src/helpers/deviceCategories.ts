import { CategoryType } from './CategoryType';
import { MultipleFormControlOption } from './MultipleFormControlOption';

export const deviceCategoriesOptions: MultipleFormControlOption<CategoryType>[] = [
  {
    commonName: 'Audiovisual',
    value: 'AUDIOVISUALS',
  },
  {
    commonName: 'Clase',
    value: 'CLASSROOM',
  },
  {
    commonName: 'Material de Laboratorio',
    value: 'LABORATORY_MATERIALS',
  },
  {
    commonName: 'Modelos',
    value: 'MODELS',
  },
] as const;
