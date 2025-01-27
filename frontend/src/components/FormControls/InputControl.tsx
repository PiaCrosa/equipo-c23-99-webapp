import { FieldValues, Path, UseFormRegister } from 'react-hook-form';
import { controlClasses } from './controlClasses'
import { ControlLabelContainer } from './ControlLabelContainer';

interface InputControlProps<T extends FieldValues> {
  propertyName: Path<T>,
  register: UseFormRegister<T>,
  defaultValue: string | number,
  commonName: string,
}

const InputControl = <T extends FieldValues>(
  { register, propertyName, commonName, defaultValue }: InputControlProps<T>
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
          defaultValue={defaultValue}
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
