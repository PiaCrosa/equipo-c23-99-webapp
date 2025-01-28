import { InputControl } from '../../components/FormControls/InputControl';
import { RadioGroupControl } from '../../components/FormControls/RadioGroupControl';
import { UseFormRegister } from 'react-hook-form';
import { AddEditUsersForm } from '../../models/AddEditUsersForm';
import { roleControlOptions } from '../../helpers/roleControlOptions';

interface AddEditUsersInputsProps {
  register: UseFormRegister<AddEditUsersForm>,
  usersForm: AddEditUsersForm,
}

const roleOptions = roleControlOptions;

const AddEditUsersInputs = (
  { register, usersForm }: AddEditUsersInputsProps
) => {
  return (
    <>
      <InputControl
        register={register}
        propertyName='dni'
        commonName='DNI'
        defaultValue={usersForm.dni}
      />
      <InputControl
        register={register}
        propertyName='name'
        commonName='Nombre completo'
        defaultValue={usersForm.name}
      />
      <InputControl
        register={register}
        propertyName='email'
        commonName='Email'
        defaultValue={usersForm.email}
      />
      <RadioGroupControl
        register={register}
        propertyName='role'
        options={roleOptions}
        commonName='Rol de usuario'
        defaultValue={usersForm.role}
      />
      <InputControl
        register={register}
        propertyName='institution_cue'
        commonName='CUE de institución'
        defaultValue={usersForm.institution_cue}
      />
      <InputControl
        register={register}
        propertyName='pass1'
        commonName='Contraseña'
        defaultValue={usersForm.pass1}
        type='password'
      />
      <InputControl
        register={register}
        propertyName='pass2'
        commonName='Confirmar contraseña'
        defaultValue={usersForm.pass2}
        type='password'
      />
    </>
  )
}

export {
  AddEditUsersInputs,
}