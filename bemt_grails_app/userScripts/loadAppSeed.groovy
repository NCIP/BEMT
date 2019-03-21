import com.provia.bemt.domain.*

println 'Loading Application Seed Data....'
def pet = new ProformaExpenseType(name: 'Marketing and Sales', sequence: 1).save(flush:true, failOnError:true)
pet = new ProformaExpenseType(name: 'Informatics Computers and Electronics', sequence: 2).save(flush:true, failOnError:true)
pet = new ProformaExpenseType(name: 'Professional Services', sequence: 3).save(flush:true, failOnError:true)
pet = new ProformaExpenseType(name: 'Facilities', sequence: 4).save(flush:true, failOnError:true)
pet = new ProformaExpenseType(name: 'Ongoing Lab Supply Expense', sequence: 5).save(flush:true, failOnError:true)
pet = new ProformaExpenseType(name: 'Other', sequence: 6).save(flush:true, failOnError:true)

//Supply Types
//Collection
def supplyParent = new SupplyType(name:"Collection", parent: null).save() 
def supplyType = new SupplyType(name:"Needles", parent: supplyParent).save() 

supplyType = new SupplyType(name:"Lancet", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Blood spot cards", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Collection Tube/Cap (examples: Fluid 5ml, Fluid 10ml)", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Vacutainer / Tubes", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Specimen Collection Containers/Kits (examples: Swab, Vials, Wide Mouth, Cups, etc)", parent: supplyParent).save() 

//Preparation / Bioprocessing
supplyParent = new SupplyType(name:"Preparation / Bioprocessing", parent: null).save() 
supplyType = new SupplyType(name:"Cutting Board Paper", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Microtome blades", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Dissecting blades", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Formalin", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Disposable freezing container", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Transfer plastic pipette, disposable", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Pipette Tips", parent: supplyParent).save() 

//Storage / Sample Management
supplyParent = new SupplyType(name:"Storage / Sample Management", parent: null).save() 
supplyType = new SupplyType(name:"Cryovial tube/cap (examples: .5ml, 2.0ml, etc.)", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Storage tubes (examples: 5ml, 12ml, 500 µL)", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Storage Containers (examples: FFPE Tissue Cassettes, Clamshell, Plastic Cassettes, Aluminum Vials)", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Slides/Coverslips (examples: Microscope, Microarray, etc)", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Storage Supplies (examples: Box, Dividers, Labels, etc)", parent: supplyParent).save() 

//Analysis / Quality Control
supplyParent = new SupplyType(name:"Analysis/Quality Control", parent: null).save() 
supplyType = new SupplyType(name:"ImmunoAssay", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Reagents", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Quality Controls", parent: supplyParent).save() 

//Transport / Distribution
supplyParent = new SupplyType(name:"Transport / Distribution", parent: null).save() 
supplyType = new SupplyType(name:"Plastic biohazard bags", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Polyethylene Bags", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Shipping Containers (examples: Slide Box)", parent: supplyParent).save() 
supplyType = new SupplyType(name:"Shipping Supplies (examples: Gel Packs, Dry Ice, etc)", parent: supplyParent).save() 

//Safety / Hygiene
supplyParent = new SupplyType(name:"Safety / Hygiene", parent: null).save() 
supplyType = new SupplyType(name:"Gloves", parent: supplyParent).save() 

//Other
supplyParent = new SupplyType(name:"Other", parent: null).save() 
supplyType = new SupplyType(name:"Other", parent: supplyParent).save() 

def ds = new SupplyDefault(type: supplyType, name: "Default Unit 1", unitPrice: 0.0, units: "piece").save()

def spec = new ProductType(name: "Specimen/Product").save()

//TISSUE
def parent = new SpecimanServiceType(name: "Tissue", parent: null, type: spec).save()
def prodType = new SpecimanServiceType(name: "Bone", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Bone marrow", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "H&E slide, normal tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "H&E slide, quadruplet (QUAD) tissue set", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "H&E slide, tumor and adjacent normal tissue FFPE", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "H&E slide, tumor tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Ligament", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Muscle", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Nerve", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Normal tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Normal tissue FFPE block", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Normal TMA block", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Post mortem tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Quadruplet (QUAD) tissue set", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Skin", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tissue biopsy", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tissue culture", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tumor and adjacent normal tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tumor and adjacent normal tissue FFPE blocks", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tumor and adjacent normal TMA blocks", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tumor tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tumor tissue FFPE block", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Tumor TMA block", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Whole organ with tumor and adjacent normal tissues", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Whole organ, with normal tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Whole organ, with tumor tissue", parent: parent, type: spec).save()

//FLUID
parent = new SpecimanServiceType(name: "Fluid", parent: null, type: spec).save()
prodType = new SpecimanServiceType(name: "Blood culture", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Buffy coat", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Buffy coat, clot from tube", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Cerebrospinal fluid (CSF)", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Cord blood", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Dried blood spot card", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Fluid", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Peripheral blood", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Peripheral blood mononuclear cells (PBMC's)", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Plasma", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Pleural fluid", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Saliva", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Serum", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Sperm", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Sputum", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Synovial fluid", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Urine", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Whole blood", parent: parent, type: spec).save()

//Nucleic Acids
parent = new SpecimanServiceType(name: "Nucleic Acids", parent: null, type: spec).save()
prodType = new SpecimanServiceType(name: "microarray (DNA, RNA, protein)", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "microDNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "microprotein", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "microRNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Protein", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "RNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "RNA, blood", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "RNA, saliva", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "RNA, tissue", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Cell free DNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Cell free RNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "DNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "DNA, blood", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "DNA, other", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "DNA, saliva", parent: parent, type: spec).save()

//Cells
parent = new SpecimanServiceType(name: "Cells", parent: null, type: spec).save()
prodType = new SpecimanServiceType(name: "CBMC's", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Bone marrow smears, stained", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Bone marrow smears, unstained", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Buccal cells", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Cell lines", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Cells", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Circulating cell free DNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Circulating cell free RNA", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Circulating tumor cells (CTC)", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Culture", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Erythrocytes", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Induced pleuripotent stem (IPS) cells", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Lymphocytes", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Protein lysate", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Red blood cells (RBC's)", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Stem cells", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "White blood cells (WBC's)", parent: parent, type: spec).save()

//other
parent = new SpecimanServiceType(name: "Other", parent: null, type: spec).save()
prodType = new SpecimanServiceType(name: "Blood clot, other", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Feces", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Fingernail clippings", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Hair", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Image of H&E slide, any", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Nail", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Other", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Pellet (RBC, WBC, etc.)", parent: parent, type: spec).save()
prodType = new SpecimanServiceType(name: "Toenail clippings", parent: parent, type: spec).save()


//SERVICES
def serv = new ProductType(name: "Service").save()

//Collection
parent = new SpecimanServiceType(name: "Collection", parent: null, type: serv).save()
prodType = new SpecimanServiceType(name: "Annual biospecimen/project collection report", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Annual biospecimen/project collection report", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Biobank consumable/supply ordering support", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Biospecimen procedure/SOP writing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Biobanking project/protocol consultation", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Biospecimen protocol and/or consent writing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Biospecimen protocol regulatory submission", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Biobank technical support, in-or out-sourced", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Evaluating and/or consenting donors for specimen and/or data collection", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Study cohort selection", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample collection", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Tissue/organ retrieval and handling (e.g. post mortem, transplant)", parent: parent, type: serv).save()

//Preparation/Bioprocessing
parent = new SpecimanServiceType(name: "Preparation/Bioprocessing", parent: null, type: serv).save()
prodType = new SpecimanServiceType(name: "DNA extraction", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Establishment of cell lines", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Protein microarrays ('Protein chip')", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Protein extraction", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "RNA extraction", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Tissue microarray building", parent: parent, type: serv).save()

//Storage/Sample management
parent = new SpecimanServiceType(name: "Storage/Sample management", parent: null, type: serv).save()
prodType = new SpecimanServiceType(name: "Biospecimen inventory management database consultation", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Chart extraction", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Data mining", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Database work", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample aliquotting", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample bioprocessing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample collection inventory management", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample collection inventory management, transfer and/or relocation", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample collection inventory management, culling", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample collection, inventory management, disaster recovery", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample storage and monitoring", parent: parent, type: serv).save()

//Analysis/Quality
parent = new SpecimanServiceType(name: "Analysis/Quality", parent: null, type: serv).save()
prodType = new SpecimanServiceType(name: "Biobank inventory management database quality/best practice review audit", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Biospecimen proficiency testing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Cell viability", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Concentration/purity testing by spectrophotometry (DNA, RNA, etc.)", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "DNA integrity testing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "DNA/RNA concentration/purity testing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "DNA sequencing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "DNA quantification", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "dsDNA quantity determination", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Gene expression profiling (DNA, RNA, protein)", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Genetic testing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Immunohistochemistry (IHC) testing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "In-situ hybridization (ISH) testing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Karyotyping", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Mass spectrometry 2-D protein profiling", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Mass spectrometry protein profiling, isotope tagging method", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Microsatellite fingerprinting", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Pathology case review (clinical diagnosis/morphology)", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Pathology consult/case review, external", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Pathology consult/case review", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Pathology report copy", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "RNA Integrity number (RIN) testing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "RNA sequencing", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample collection inventory management quality/best practice review audit", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Specimen biosketch report", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample concentration/purity testing, other", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "SNP genotyping", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Tissue histology", parent: parent, type: serv).save()

//Transport/Distribution
parent = new SpecimanServiceType(name: "Transport/Distribution", parent: null, type: serv).save()
prodType = new SpecimanServiceType(name: "Packaging specimen shipments", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample distribution", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Specimen collection kit assembly and shipping", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Specimen packaging, flat fee (does not include shipping fees)", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Specimen transport", parent: parent, type: serv).save()

//Safety/Hygiene
parent = new SpecimanServiceType(name: "Safety/Hygiene", parent: null, type: serv).save()
prodType = new SpecimanServiceType(name: "Autoclave", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Decontamination", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Safety/Hygiene Related", parent: parent, type: serv).save()

parent = new SpecimanServiceType(name: "Other", parent: null, type: serv).save()
prodType = new SpecimanServiceType(name: "Education and training", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Equipment use", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Facility/Laboratory use", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Return of biospecimen research results to study participants and/or specimen donors", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Sample/vial relabeling", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Supply use", parent: parent, type: serv).save()
prodType = new SpecimanServiceType(name: "Other", parent: parent, type: serv).save()



//Executive
def lt = new LaborType(name: "Executive").save()
def lt1 = new LaborType(name: "Chief Operating Officer (COO)", parent: lt).save()
lt1 = new LaborType(name: "Chief Technology Officer (CTO)", parent: lt).save()
lt1 = new LaborType(name: "Chief Financial Officer (CFO)", parent: lt).save()
lt1 = new LaborType(name: "Chief Commercial Officer (CCO)", parent: lt).save()
lt1 = new LaborType(name: "Chief scientific officer (CSO)", parent: lt).save()
lt1 = new LaborType(name: "Deputy Director", parent: lt).save()
lt1 = new LaborType(name: "Board Member (non-scientific)", parent: lt).save()

//Management
lt = new LaborType(name: "Management").save()
lt1 = new LaborType(name: "Biobank Manager", parent: lt).save()
lt1 = new LaborType(name: "General Manager", parent: lt).save()

//Director
lt = new LaborType(name: "Director").save()
lt1 = new LaborType(name: "Facility Director", parent: lt).save()
lt1 = new LaborType(name: "Technical/Executive Director", parent: lt).save()
lt1 = new LaborType(name: "Associate/Scientific Director", parent: lt).save()
lt1 = new LaborType(name: "Deputy Technical Coordinator", parent: lt).save()
lt1 = new LaborType(name: "Head/Group/Team Leader", parent: lt).save()

//Lab Operations
lt = new LaborType(name: "Lab Operations").save()
lt1 = new LaborType(name: "Assistant Technician", parent: lt).save()
lt1 = new LaborType(name: "Lab Technician", parent: lt).save()
lt1 = new LaborType(name: "Lab Manager/Supervisor", parent: lt).save()
lt1 = new LaborType(name: "Laboratory Assistant", parent: lt).save()
lt1 = new LaborType(name: "Biobank/Repository Coordinator", parent: lt).save()
lt1 = new LaborType(name: "Repository Manager", parent: lt).save()        
lt1 = new LaborType(name: "Sample Manager", parent: lt).save()
lt1 = new LaborType(name: "Quality Manager", parent: lt).save()
lt1 = new LaborType(name: "Quality Program Coordinator", parent: lt).save()
lt1 = new LaborType(name: "Program/Project Manager", parent: lt).save()
lt1 = new LaborType(name: "Molecular Biologist", parent: lt).save()


//Histology/Pathology
lt = new LaborType(name: "Histology/Pathology").save()
lt1 = new LaborType(name: "Histology Technician", parent: lt).save()
lt1 = new LaborType(name: "Pathology Assistant", parent: lt).save()
lt1 = new LaborType(name: "Pathologist", parent: lt).save()

//Research
lt = new LaborType(name: "Research").save()
lt1 = new LaborType(name: "Clinical Research Associate", parent: lt).save()
lt1 = new LaborType(name: "Clinical Research Coordinator", parent: lt).save()
lt1 = new LaborType(name: "Data Manager", parent: lt).save()
lt1 = new LaborType(name: "Research Assistant", parent: lt).save()
lt1 = new LaborType(name: "Research/Study Nurse", parent: lt).save()
lt1 = new LaborType(name: "Individual Researcher/Scientist", parent: lt).save()
lt1 = new LaborType(name: "Clinical Resident/Fellow", parent: lt).save()
lt1 = new LaborType(name: "Principal Investigator", parent: lt).save()

//Informatics and Data Management
lt = new LaborType(name: "Informatics and Data Management").save()
lt1 = new LaborType(name: "Bioinformatics Professional", parent: lt).save()
lt1 = new LaborType(name: "Biostatistician", parent: lt).save()
lt1 = new LaborType(name: "Systems Analyst", parent: lt).save()
lt1 = new LaborType(name: "Programmer", parent: lt).save()

//Commercial
lt = new LaborType(name: "Commercial").save()
lt1 = new LaborType(name: "Business Manager", parent: lt).save()
lt1 = new LaborType(name: "Customer Product/Service Representative", parent: lt).save()
lt1 = new LaborType(name: "Product Manager", parent: lt).save()

//Advisory
lt = new LaborType(name: "Advisory").save()
lt1 = new LaborType(name: "Advisor/Consultant", parent: lt).save()
lt1 = new LaborType(name: "Advisory Board Member (scientific/technical/ELSI, etc.)", parent: lt).save()

//Other
lt = new LaborType(name: "Other").save()
lt1 = new LaborType(name: "Administrative/Executive Assistant", parent: lt).save()
lt1 = new LaborType(name: "Accountant", parent: lt).save()
lt1 = new LaborType(name: "Financial Manager", parent: lt).save()
lt1 = new LaborType(name: "Bioethicist", parent: lt).save()
lt1 = new LaborType(name: "Biojurist", parent: lt).save()
lt1 = new LaborType(name: "Lawyer", parent: lt).save()
lt1 = new LaborType(name: "Genetic Counselor", parent: lt).save()
lt1 = new LaborType(name: "Other", parent: lt).save()

//LABOR END

//EQUIPMENT START

//Collection
def et1 = new EquipmentType(name: "Collection").save()
def et = new EquipmentType(name: "Bar-code flat scanner, multi-tray", parent: et1).save()
et = new EquipmentType(name: "Bar-code reader", parent: et1).save()
et = new EquipmentType(name: "Bulk label printer, bench-top", parent: et1).save()
et = new EquipmentType(name: "Bulk sample labeler, bench-top", parent: et1).save()
et = new EquipmentType(name: "RFID flat scanner, multi-tray", parent: et1).save()
et = new EquipmentType(name: "Tissue cassette labeler, bench-top, automated", parent: et1).save()

//Preparation / Bioprocessing
et1 = new EquipmentType(name: "Preparation / Bioprocessing").save()
et = new EquipmentType(name: "Buffy coat isolation instrument, automated", parent: et1).save()
et = new EquipmentType(name: "Centrifuge, refrigerated, bench-top", parent: et1).save()
et = new EquipmentType(name: "Centrifuge, refrigerated, stand-alone", parent: et1).save()
et = new EquipmentType(name: "Cold table/cryogenic workbench", parent: et1).save()
et = new EquipmentType(name: "Controlled rate freezer", parent: et1).save()
et = new EquipmentType(name: "Controlled rate freezing container system", parent: et1).save()
et = new EquipmentType(name: "Cryostat", parent: et1).save()
et = new EquipmentType(name: "DNA extraction station, automated", parent: et1).save()
et = new EquipmentType(name: "Frozen specimen aliquotter, stand-alone", parent: et1).save()
et = new EquipmentType(name: "Frozen specimen aliquotter, bench-top", parent: et1).save()
et = new EquipmentType(name: "Incubator, manual", parent: et1).save()
et = new EquipmentType(name: "Incubator, automated", parent: et1).save()
et = new EquipmentType(name: "Laboratory workbench", parent: et1).save()
et = new EquipmentType(name: "Laminar flow cabinet/hood", parent: et1).save()
et = new EquipmentType(name: "Microplate dispenser/sample processor, bench-top, automated", parent: et1).save()
et = new EquipmentType(name: "Microtome", parent: et1).save()
et = new EquipmentType(name: "Nucleic acid/protein extraction instrument, automated", parent: et1).save()
et = new EquipmentType(name: "PBMC isolation instrument, automated", parent: et1).save()
et = new EquipmentType(name: "Plate piercer, bench-top, automated", parent: et1).save()
et = new EquipmentType(name: "Plate scanner, flatbed", parent: et1).save()
et = new EquipmentType(name: "Plate/tube sealer module, bench-top, automated", parent: et1).save()
et = new EquipmentType(name: "Sample capper-de-capper, bench-top, automated", parent: et1).save()
et = new EquipmentType(name: "Sample/vial filling station, bench-top, automated", parent: et1).save()
et = new EquipmentType(name: "Sample/vial filling station, stand-alone, automated", parent: et1).save()
et = new EquipmentType(name: "Slide autostainer", parent: et1).save()
et = new EquipmentType(name: "SPIN tissue processor, microwave", parent: et1).save()
et = new EquipmentType(name: "Stem cell culture instrument, automated", parent: et1).save()
et = new EquipmentType(name: "Tissue embedding station", parent: et1).save()
et = new EquipmentType(name: "Tissue microarray (TMA) instrument, automated", parent: et1).save()
et = new EquipmentType(name: "Tissue microarray (TMA) instrument, manual", parent: et1).save()
et = new EquipmentType(name: "Tissue processor, walk-away", parent: et1).save()
et = new EquipmentType(name: "Tissue processor, rapid conventional", parent: et1).save()
et = new EquipmentType(name: "Waterbath", parent: et1).save()
et = new EquipmentType(name: "Waterbath, cold", parent: et1).save()
et = new EquipmentType(name: "Waterbath, hot", parent: et1).save()

//Storage / Sample management
et1 = new EquipmentType(name: "Storage / Sample management").save()
et = new EquipmentType(name: "Biostore, -20°C, automated", parent: et1).save()
et = new EquipmentType(name: "Biostore, -20°C to -40°C, automated", parent: et1).save()
et = new EquipmentType(name: "Biostore, -20°C to -80°C, automated", parent: et1).save()
et = new EquipmentType(name: "Biostore, -80°C, automated", parent: et1).save()
et = new EquipmentType(name: "Biostore, other", parent: et1).save()
et = new EquipmentType(name: "Block filing system", parent: et1).save()
et = new EquipmentType(name: "Block and slide storage fire safe", parent: et1).save()
et = new EquipmentType(name: "Cold room/walk-in", parent: et1).save()
et = new EquipmentType(name: "Cryogenic cabinet freezer", parent: et1).save()
et = new EquipmentType(name: "Cryotank, -20°C to -148°C LN²", parent: et1).save()
et = new EquipmentType(name: "Cryotank, -149°C to -190°C LN²", parent: et1).save()
et = new EquipmentType(name: "Cryotank, -20°C to -190°C LN²", parent: et1).save()
et = new EquipmentType(name: "Freezer -80 to -190°C, automated", parent: et1).save()
et = new EquipmentType(name: "Freezer, -80°C, chest, manual", parent: et1).save()
et = new EquipmentType(name: "Freezer,-148°C, chest, manual", parent: et1).save()
et = new EquipmentType(name: "Freezer, -80°C, vertical, manual", parent: et1).save()
et = new EquipmentType(name: "Freezer,-148°C, vertical, manual", parent: et1).save()
et = new EquipmentType(name: "Freezer monitoring system", parent: et1).save()
et = new EquipmentType(name: "Freezer", parent: et1).save()
et = new EquipmentType(name: "Laser capture microdissection system", parent: et1).save()
et = new EquipmentType(name: "Liquid handler", parent: et1).save()
et = new EquipmentType(name: "Mobile cryotank rack lifts system", parent: et1).save()
et = new EquipmentType(name: "Plasmid processing station", parent: et1).save()
et = new EquipmentType(name: "Processing station, other", parent: et1).save()
et = new EquipmentType(name: "Protective shipping carton, industrial", parent: et1).save()
et = new EquipmentType(name: "Refrigerator, other", parent: et1).save()
et = new EquipmentType(name: "Refrigerator, -20°C to -40°C", parent: et1).save()
et = new EquipmentType(name: "Refrigerator, - 0°C to -9°C", parent: et1).save()
et = new EquipmentType(name: "Sample/tube picking & sorting station, bench-top", parent: et1).save()
et = new EquipmentType(name: "Sample/tube picking & sorting station, stand-alone", parent: et1).save()
et = new EquipmentType(name: "Sample volume tube/plate auditor checking station, stand-alone", parent: et1).save()
et = new EquipmentType(name: "Slide filing system", parent: et1).save()
et = new EquipmentType(name: "Temperature monitor probes", parent: et1).save()
et = new EquipmentType(name: "Tube puncher and selecting module, bench-top", parent: et1).save()


//Analysis / Quality control
et1 = new EquipmentType(name: "Analysis / Quality control").save()
et = new EquipmentType(name: "Amplification workstation", parent: et1).save()
et = new EquipmentType(name: "Analytical balance", parent: et1).save()
et = new EquipmentType(name: "Bench rocker", parent: et1).save()
et = new EquipmentType(name: "Bench-top shaker", parent: et1).save()
et = new EquipmentType(name: "Bioanalyzer", parent: et1).save()
et = new EquipmentType(name: "Blood fractionation system, automated", parent: et1).save()
et = new EquipmentType(name: "Blot hybridization system", parent: et1).save()
et = new EquipmentType(name: "Cell counter, automated", parent: et1).save()
et = new EquipmentType(name: "Confocal microscope", parent: et1).save()
et = new EquipmentType(name: "Digital slide imager", parent: et1).save()
et = new EquipmentType(name: "DNA/RNA sequencer", parent: et1).save()
et = new EquipmentType(name: "Electrophoresis instrument", parent: et1).save()
et = new EquipmentType(name: "FISH instrument", parent: et1).save()
et = new EquipmentType(name: "Flow cytometer", parent: et1).save()
et = new EquipmentType(name: "Gene array scanner workstation", parent: et1).save()
et = new EquipmentType(name: "High through-put screening instrument", parent: et1).save()
et = new EquipmentType(name: "IHC/ISH instrument", parent: et1).save()
et = new EquipmentType(name: "Imaging system, other", parent: et1).save()
et = new EquipmentType(name: "Mass spectrometer", parent: et1).save()
et = new EquipmentType(name: "Microscope, other", parent: et1).save()
et = new EquipmentType(name: "Microscope camera", parent: et1).save()
et = new EquipmentType(name: "Nucleic acid integrity analyzer", parent: et1).save()
et = new EquipmentType(name: "Orbital bath shaker, bench-top", parent: et1).save()
et = new EquipmentType(name: "PCR thermal cycler", parent: et1).save()
et = new EquipmentType(name: "PCR workstation", parent: et1).save()
et = new EquipmentType(name: "Reagent dispenser", parent: et1).save()
et = new EquipmentType(name: "Real time PCR system", parent: et1).save()
et = new EquipmentType(name: "RNA/DNA/Protein analysis system", parent: et1).save()
et = new EquipmentType(name: "Spectrophotometer", parent: et1).save()
et = new EquipmentType(name: "Scale", parent: et1).save()
et = new EquipmentType(name: "Tissue imaging system", parent: et1).save()
et = new EquipmentType(name: "Thermal shaker", parent: et1).save()
et = new EquipmentType(name: "Transmission electron microscope", parent: et1).save()
et = new EquipmentType(name: "Vortex mixer", parent: et1).save()
et = new EquipmentType(name: "Western blot transfer system", parent: et1).save()

//Transport / Distribution
et1 = new EquipmentType(name: "Transport / Distribution").save()
et = new EquipmentType(name: "Cart", parent: et1).save()
et = new EquipmentType(name: "Cryo-cart", parent: et1).save()
et = new EquipmentType(name: "Dry vapor shippers, large", parent: et1).save()
et = new EquipmentType(name: "Large cryogenic aluminum Dewar's", parent: et1).save()

//Safety / Hygiene
et1 = new EquipmentType(name: "Safety / Hygiene").save()
et = new EquipmentType(name: "Autoclave", parent: et1).save()
et = new EquipmentType(name: "Clean room", parent: et1).save()
et = new EquipmentType(name: "Safety cabinet", parent: et1).save()

//Other
et1 = new EquipmentType(name: "Other").save()
et = new EquipmentType(name: "Camera", parent: et1).save()
et = new EquipmentType(name: "Other", parent: et1).save()


println 'Complete.'