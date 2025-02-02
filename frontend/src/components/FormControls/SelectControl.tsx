import { FieldErrors, FieldValues, Path, UseFormRegister } from 'react-hook-form';
import { controlClasses } from './controlClasses'
import { ControlLabelContainer } from './ControlLabelContainer';
import { MultipleFormControlOption } from '../../helpers/MultipleFormControlOption';

interface SelectControlProps<T extends FieldValues> {
  propertyName: Path<T>,
  register: UseFormRegister<T>,
  errors: FieldErrors<T>,
  defaultValue: string | number,
  commonName: string,
  options: MultipleFormControlOption[],
}

const SelectControl = <T extends FieldValues>(
  { register, propertyName, commonName, options, defaultValue, errors }: SelectControlProps<T>
) => {
  const {
    controlContainerClasses,
    textInputContainerClasses,
    textInputClasses,
    errorContainerClasses,
  } = controlClasses;

  return (
    <div className={controlContainerClasses}>
      <ControlLabelContainer commonName={commonName} />
      <div className={textInputContainerClasses}>
        <select
          className={textInputClasses}
          defaultValue={defaultValue}
          {...register(
            propertyName,
            { required: true }
          )}
        >
          {
            options.map((option) => {
              return (
                <option
                  key={option.value}
                  value={option.value}
                >
                  {option.commonName}
                </option>
              )
            })
          }
        </select>
        {

          errors[propertyName] ?
            <div className={errorContainerClasses}>
              {String(errors[propertyName]?.message) || `Error en ${commonName}`}
            </div>
            : <></>
        }
      </div>
    </div>
  )
}

export {
  SelectControl,
}