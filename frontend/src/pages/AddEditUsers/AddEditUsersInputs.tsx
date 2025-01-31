import { InputControl } from '../../components/FormControls/InputControl';
import { UseFormRegister } from 'react-hook-form';
import { AddEditUsersForm } from '../../models/AddEditUsersForm';

interface AddEditUsersInputsProps {
  register: UseFormRegister<AddEditUsersForm>,
}

const AddEditUsersInputs = (
  { register }: AddEditUsersInputsProps
) => {

  return (
    <>
      <InputControl
        register={register}
        propertyName='dni'
        commonName='DNI'
      />
      <InputControl
        register={register}
        propertyName='name'
        commonName='Nombre completo'
      />
      <InputControl
        register={register}
        propertyName='email'
        commonName='Email'
      />
      <InputControl
        register={register}
        propertyName='pass1'
        commonName='Contraseña'
        type='password'
      />
      <InputControl
        register={register}
        propertyName='pass2'
        commonName='Confirmar contraseña'
        type='password'
      />
    </>
  )
}

export {
  AddEditUsersInputs,
}