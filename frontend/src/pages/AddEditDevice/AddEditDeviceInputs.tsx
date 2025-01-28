import { InputControl } from '../../components/FormControls/InputControl';
import { SelectControl } from '../../components/FormControls/SelectControl';
import { RadioGroupControl } from '../../components/FormControls/RadioGroupControl';
import { UseFormRegister } from 'react-hook-form';
import { AddEditDeviceForm } from '../../models/AddEditDeviceForm';
import { deviceCategoriesOptions } from '../../helpers/deviceCategories';
import { yesNoOptions } from '../../helpers/yesNoOptions';

interface AddEditDeviceInputsProps {
  register: UseFormRegister<AddEditDeviceForm>,
  deviceForm: AddEditDeviceForm,
}

const categories = deviceCategoriesOptions;
const yesNoOptionsOptions = yesNoOptions;

const AddEditDeviceInputs = (
  { register, deviceForm }: AddEditDeviceInputsProps
) => {
  return (
    <>
      <InputControl
        register={register}
        propertyName='name'
        commonName='Nombre'
        defaultValue={deviceForm.name}
      />
      <SelectControl
        register={register}
        propertyName='category'
        commonName='Categoría'
        options={categories}
        defaultValue={deviceForm.category}
      />
      <InputControl
        register={register}
        propertyName='description'
        commonName='Descripción'
        defaultValue={deviceForm.description}
      />
      <RadioGroupControl
        register={register}
        propertyName='isAvailable'
        commonName='Status'
        options={yesNoOptionsOptions}
        defaultValue={deviceForm.isAvailable}
      />
    </>
  )
}

export {
  AddEditDeviceInputs,
}