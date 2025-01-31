import { FieldValues, Path, UseFormRegister } from 'react-hook-form';
import { controlClasses } from './controlClasses'
import { MultipleFormControlOption } from '../../helpers/MultipleFormControlOption';
import clsx from 'clsx';
import { useEffect, useState } from 'react';

interface RadioGroupControlProps<T extends FieldValues> {
  propertyName: Path<T>,
  register: UseFormRegister<T>,
  defaultValue: string | number,
  commonName: string,
  options: MultipleFormControlOption[],
}

const RadioGroupControl = <T extends FieldValues>(
  { commonName, options, propertyName, register, defaultValue }: RadioGroupControlProps<T>
) => {
  const {
    controlContainerClasses,
    labelClasses,
    labelContainerClasses,
    radioInputContainerClasses,
  } = controlClasses;

  const [selectedValue, setSelectedValue] = useState<string | number>(defaultValue);

  useEffect(() => {
    setSelectedValue(defaultValue);
  }, [defaultValue]);

  return (
    <div className={controlContainerClasses}>
      <div className={labelContainerClasses}>
        <label className={labelClasses}>
          {commonName}:
        </label>
      </div>
      <div className={radioInputContainerClasses}>
        {
          options.map((option, index) => {
            return (
              <div
                key={option.value}
                className={clsx(
                  'sm:flex sm:flex-1',
                  index === 0 ? 'sm:pl-2' : '',
                )}
              >
                <label className='
                    text-lg pr-1
                    sm:self-center
                  '
                >
                  {option.commonName}
                </label>
                <input
                  className='mt-1'
                  type='radio'
                  value={option.value}
                  checked={String(selectedValue) === String(option.value)}
                  {...register(
                    propertyName,
                  )}
                  onChange={() => setSelectedValue(option.value)}
                />
              </div>
            )
          })
        }
      </div>
    </div>
  )
}

export {
  RadioGroupControl,
}
