import pytest
from data.generate_data import generate_test_data
from pages.registration_page import RegistrationPage

@pytest.fixture
def registration_page(page, base_url):
    reg_page=RegistrationPage(page)
    reg_page.navigate(base_url)
    return reg_page


@pytest.mark.parametrize("missing_field", ["name", "DNI", "aemail", "password", "cpassword", "institution",
                                           "CUE", "iemail", "elevel", "address", "tel"])
def test_required_fields(registration_page, missing_field):
    test_data=generate_test_data(missing_field=missing_field)

    registration_page.fill_registration_form(**test_data)
    registration_page.submit_form()

    assert not registration_page.is_field_filled(getattr(registration_page, f"{missing_field}_field")), \
        f"El campo '{missing_field}' debería estar en estado inválido y mostrando tooltip"
        

def test_dni_seven_digits_error_message(registration_page):
    test_data=generate_test_data()
    test_data["DNI"]="1234567"
    
    registration_page.fill_registration_form(**test_data)
    registration_page.submit_form()
    
    error_message=registration_page.get_error_message()
    assert error_message=="El DNI es obligatorio y debe tener 8 caracteres numéricos", \
        f"Se esperaba el mensaje de error 'El DNI es obligatorio y debe tener 8 caracteres numéricos', pero se recibió '{error_message}'"
        
        
def test_dni_nine_digits_error_message(registration_page):
    test_data=generate_test_data()
    test_data["DNI"]="123456789"
    
    registration_page.fill_registration_form(**test_data)
    registration_page.submit_form()
    
    error_message=registration_page.get_error_message()
    assert error_message=="El DNI es obligatorio y debe tener 8 caracteres numéricos", \
        f"Se esperaba el mensaje de error 'El DNI es obligatorio y debe tener 8 caracteres numéricos', pero se recibió '{error_message}'"
        
        
def test_letters_phone_error_message(registration_page):
    test_data=generate_test_data()
    test_data["tel"]="letters"
    
    registration_page.fill_registration_form(**test_data)
    registration_page.submit_form()
    
    error_message=registration_page.get_error_message()
    assert error_message=="El teléfono es obligatorio y debe contener solo números", \
        f"Se esperaba el mensaje de error 'El teléfono es obligatorio y debe contener solo números', pero se recibió '{error_message}'"
        
       
def test_confirm_password_does_not_match_error_message(registration_page):
    test_data=generate_test_data()
    test_data["cpassword"]="Password2@"
    
    registration_page.fill_registration_form(**test_data)
    registration_page.submit_form()
    
    error_message=registration_page.get_error_message()
    assert error_message=="Las contraseñas no coinciden", \
        f"Se esperaba el mensaje de error 'Las contraseñas no coinciden', pero se recibió '{error_message}'"   