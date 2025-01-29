import { FieldValues, Path, UseFormRegister } from 'react-hook-form';
import { controlClasses } from './controlClasses'
import { ControlLabelContainer } from './ControlLabelContainer';
import { HTMLInputTypeAttribute } from 'react';

interface InputControlProps<T extends FieldValues> {
  propertyName: Path<T>,
  register: UseFormRegister<T>,
  commonName: string,
  type?: HTMLInputTypeAttribute 
}

const InputControl = <T extends FieldValues>(
  {
    register, propertyName, commonName, type = 'text'
  }: InputControlProps<T>
) => {
  const {
    controlContainerClasses,
    textInputContainerClasses,
    textInputClasses,
  } = controlClasses;

  return (
    <div className={controlContainerClasses}>
      <ControlLabelContainer commonName={commonName}/>
      <div className={textInputContainerClasses}>
        <input
          className={textInputClasses}
          type={type}
          {...register(
            propertyName,
            { required: true }
          )}
        />
      </div>
    </div>
  )
}

export {
  InputControl,
}
