-- Table: Customer
-- Stores information about the policyholders.
CREATE TABLE Customer (
    customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(50),
    address TEXT,
    date_of_birth DATE,
    registration_date DATE NOT NULL
);

-- Table: risk_assessment
-- Stores risk assessment details for each customer.
CREATE TABLE risk_assessment (
    assessment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    risk_score INT,
    -- Assuming 'low', 'medium', 'high' as possible enum values
    risk_category ENUM('Low', 'Medium', 'High'),
    assessment_date DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

-- Table: policy_type
-- Stores details about different types of insurance policies available.
CREATE TABLE policy_type (
    policy_type_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    base_premium DECIMAL(10, 2) NOT NULL
);

-- Table: policy
-- The central table, storing core policy information.
CREATE TABLE policy (
    policy_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    policy_type_id BIGINT NOT NULL,
    issue_date DATE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    -- Example statuses: 'Active', 'Expired', 'Cancelled'
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (policy_type_id) REFERENCES policy_type(policy_type_id)
);

-- Table: Endorsement
-- Stores amendments or changes made to an existing policy.
CREATE TABLE Endorsement (
    endorsement_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_id BIGINT NOT NULL,
    field_change VARCHAR(255) NOT NULL,
    change_type VARCHAR(255),
    old_value TEXT, -- Using TEXT to accommodate various data types
    new_value TEXT, -- Using TEXT to accommodate various data types
    description TEXT,
    effective_date DATE NOT NULL,
    FOREIGN KEY (policy_id) REFERENCES policy(policy_id)
);

-- Table: coverage
-- Details the specific coverages included in a policy.
CREATE TABLE coverage (
    coverage_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_id BIGINT NOT NULL,
    coverage_type VARCHAR(255) NOT NULL,
    coverage_limit DECIMAL(15, 2) NOT NULL,
    description TEXT,
    FOREIGN KEY (policy_id) REFERENCES policy(policy_id)
);

-- Table: renewal
-- Tracks the renewal history of a policy.
CREATE TABLE renewal (
    renewal_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_id BIGINT NOT NULL,
    renewal_date DATE NOT NULL,
    new_end_date DATE NOT NULL,
    -- Example statuses: 'Completed', 'Pending', 'Declined'
    status VARCHAR(50),
    FOREIGN KEY (policy_id) REFERENCES policy(policy_id)
);

-- Table: cancellation
-- Stores information about policy cancellations.
CREATE TABLE cancellation (
    cancellation_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_id BIGINT NOT NULL,
    cancellation_date DATE NOT NULL,
    reason TEXT,
    -- Assuming cancellation can be initiated by the insurer or the insured
    cancellation_source ENUM('Insurer', 'Insured'),
    FOREIGN KEY (policy_id) REFERENCES policy(policy_id)
);

-- Table: claim
-- Stores details about claims made against a policy.
CREATE TABLE claim (
    claim_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_id BIGINT NOT NULL,
    incident_date DATE NOT NULL,
    submission_date DATE NOT NULL,
    approval_date DATE,
    payout_date DATE,
    amount_requested DECIMAL(15, 2) NOT NULL,
    amount_approved DECIMAL(15, 2),
    -- Example statuses: 'Submitted', 'In Review', 'Approved', 'Denied'
    status VARCHAR(50),
    incident_type VARCHAR(255),
    FOREIGN KEY (policy_id) REFERENCES policy(policy_id)
);

-- Table: payment
-- Tracks payments made for a policy.
CREATE TABLE payment (
    payment_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    policy_id BIGINT NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    amount DECIMAL(10, 2) NOT NULL,
    payment_mode VARCHAR(50),
    grace_period_days INT,
    -- Example statuses: 'Paid', 'Due', 'Overdue'
    status VARCHAR(50),
    FOREIGN KEY (policy_id) REFERENCES policy(policy_id)
);

-- 1. Insert into Customer table
INSERT INTO Customer (name, email, phone, address, date_of_birth, registration_date)
VALUES
('John Smith', 'john.smith@example.com', '555-0101', '123 Maple Street, Anytown, USA', '1985-06-15', '2023-01-10'),
('Jane Doe', 'jane.doe@example.com', '555-0102', '456 Oak Avenue, Sometown, USA', '1992-09-20', '2023-02-20');

-- 2. Insert into risk_assessment table
-- Assuming customer_id 1 is John Smith and 2 is Jane Doe
INSERT INTO risk_assessment (customer_id, risk_score, risk_category, assessment_date)
VALUES
(1, 75, 'Medium', '2023-01-11'),
(2, 45, 'Low', '2023-02-21');

-- 3. Insert into policy_type table
INSERT INTO policy_type (name, description, base_premium)
VALUES
('Auto Insurance - Standard', 'Standard coverage for personal vehicles.', 650.00),
('Homeowners Insurance - Plus', 'Comprehensive coverage for home and property.', 950.00);

-- 4. Insert into policy table
-- John Smith (1) gets Auto Insurance (1), Jane Doe (2) gets Homeowners Insurance (2)
INSERT INTO policy (customer_id, policy_type_id, issue_date, start_date, end_date, status)
VALUES
(1, 1, '2023-01-15', '2023-02-01', '2024-02-01', 'Active'),
(2, 2, '2023-03-01', '2023-03-15', '2024-03-15', 'Active');

-- 5. Insert into Endorsement table
-- An endorsement to change the address on John Smith's policy (policy_id 1)
INSERT INTO Endorsement (policy_id, field_change, change_type, old_value, new_value, description, effective_date)
VALUES
(1, 'address', 'Update', '123 Maple Street, Anytown, USA', '789 Pine Lane, Anytown, USA', 'Customer moved to a new address.', '2023-07-01'),
(2, 'coverage_limit', 'Increase', '250000.00', '300000.00', 'Increased property coverage limit.', '2023-08-15');

-- 6. Insert into coverage table
INSERT INTO coverage (policy_id, coverage_type, coverage_limit, description)
VALUES
(1, 'Collision', 25000.00, 'Covers damage to the insured vehicle from a collision.'),
(2, 'Fire and Theft', 300000.00, 'Covers loss or damage from fire and theft.');

-- 7. Insert into renewal table
-- Let's assume John's policy (policy_id 1) was renewed for another year
INSERT INTO renewal (policy_id, renewal_date, new_end_date, status)
VALUES
(1, '2024-01-20', '2025-02-01', 'Completed'),
(2, '2024-03-10', '2025-03-15', 'Pending');


-- 8. Insert into cancellation table
-- Create a new policy for cancellation purposes and then cancel it
INSERT INTO policy (customer_id, policy_type_id, issue_date, start_date, end_date, status)
VALUES (1, 2, '2024-01-01', '2024-01-01', '2025-01-01', 'Cancelled'); -- This will be policy_id 3
INSERT INTO cancellation (policy_id, cancellation_date, reason, cancellation_source)
VALUES
(3, '2024-06-01', 'Customer sold the property.', 'Insured'),
(2, '2024-09-01', 'Non-payment of premium.', 'Insurer');


-- 9. Insert into claim table
-- Jane Doe (policy_id 2) files a claim
INSERT INTO claim (policy_id, incident_date, submission_date, approval_date, payout_date, amount_requested, amount_approved, status, incident_type)
VALUES
(2, '2023-09-10', '2023-09-12', '2023-09-25', '2023-09-30', 1500.00, 1450.00, 'Approved', 'Water Damage'),
(1, '2023-11-05', '2023-11-06', NULL, NULL, 2200.00, NULL, 'In Review', 'Fender Bender');

-- 10. Insert into payment table
INSERT INTO payment (policy_id, due_date, payment_date, amount, payment_mode, grace_period_days, status)
VALUES
(1, '2023-02-01', '2023-01-28', 650.00, 'Credit Card', 15, 'Paid'),
(2, '2023-03-15', '2023-03-15', 950.00, 'Bank Transfer', 15, 'Paid');

